package engine.world.components

import engine.world.*
import extensions.*
import math.*
import org.lwjgl.opengl.GL45C.*
import wrappers.opengl.*
import kotlin.properties.*

class Camera(node: Node) : Component(node), Updatable {

    private val transform = node add Transform::class


    val buffer = device.buffer(Int.SIZE_BYTES.toLong() * 16, UniformBuffer, DynamicStorage)


    var fieldOfView by Delegates.observable(0.7854f) { _, _, _ -> invalidateProjection() }
    var aspectRatio by Delegates.observable(640f / 480f) { _, _, _ -> invalidateProjection() }
    var nearPlaneClipping by Delegates.observable(0.05f) { _, _, _ -> invalidateProjection() }
    var farPlaneClipping by Delegates.observable(500f) { _, _, _ -> invalidateProjection() }


    var output: Framebuffer? = null


    // Projection Matrix
    private var projectionInvalid = true

    var projectionMatrix = ProjectionMatrix.identity
        get() {
            if (projectionInvalid) validateProjection()
            return field
        }
        private set(value) {
            buffer.setSubData(0, value.matrix.toFloatArray())
            field = value
        }

    private fun invalidateProjection() {
        projectionInvalid = true
    }

    private fun validateProjection() {
        projectionMatrix = ProjectionMatrix.perspectiveFieldOfView(fieldOfView, aspectRatio, nearPlaneClipping, farPlaneClipping)
        projectionInvalid = false
    }


    private val geometryFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.image2d(640, 480, RGB8),
        GL_COLOR_ATTACHMENT1 to device.image2d(640, 480, RGB8),
        GL_DEPTH_ATTACHMENT to device.image2d(640, 480, DEPTH24)
    )

    private val state = DeviceState(
        geometryFramebuffer,
        cull = true,
        blend = false,
        depth = true,
        stencil = false
    )


    override fun update(delta: Float) {

        val output = output ?: return

        if (projectionInvalid) validateProjection()


        device.enqueue(state) {

            bindUniformBuffer(0, transform.buffer)
            bindUniformBuffer(1, buffer)

            // -- GEOMETRY PASS

            for (renderer in scene findAll Renderable::class) renderer.draw(this)

            copyFramebuffer(
                src = geometryFramebuffer,
                srcRect = Int4(0, 0, geometryFramebuffer.width, geometryFramebuffer.height),
                dst = output,
                dstRect = Int4(0, 0, 640, 480),
                mask = CopyFramebufferMask.ColorBuffer,
                filter = CopyFramebufferFilter.Nearest
            )
            clearFramebuffer()

        }

    }

}