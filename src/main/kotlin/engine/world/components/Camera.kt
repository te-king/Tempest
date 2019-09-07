package engine.world.components

import engine.world.Node
import engine.world.Renderable
import engine.world.Updatable
import extensions.findAll
import math.Int4
import wrappers.glfw.Window
import math.ProjectionMatrix
import org.lwjgl.opengl.GL45C.*
import wrappers.opengl.*

class Camera (node: Node) : Component(node), Updatable {

    private val transform = node add Transform::class


    //
    val buffer = device.buffer(Int.SIZE_BYTES.toLong() * 16, BufferUsage.DYNAMIC)


    // Parameters
    var fieldOfView: Float = Math.toRadians(45.0).toFloat()
        set(value) {
            invalidateProjection()
            field = value
        }

    var aspectRatio: Float = 640f / 480f
        set(value) {
            invalidateProjection()
            field = value
        }

    var nearPlaneClipping: Float = 0.05f
        set(value) {
            invalidateProjection()
            field = value
        }

    var farPlaneClipping: Float = 500f
        set(value) {
            invalidateProjection()
            field = value
        }


    var outputFramebuffer = Framebuffer.default


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
        GL_COLOR_ATTACHMENT0 to device.image2d(TextureFormat.RGB8, 640, 480),
        GL_COLOR_ATTACHMENT1 to device.image2d(TextureFormat.RGB8, 640, 480),
        GL_DEPTH_ATTACHMENT to device.image2d(TextureFormat.DEPTH24, 640, 480)
    )

    private val illuminationFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.image2d(TextureFormat.RGB8, 640, 480)
    )


    override fun update(delta: Float) {

        if (projectionInvalid) validateProjection()

        // Render scene into geometryFramebuffer
        device.commandBuffer().apply {

            // Bind camera transform
            bindBuffer(0, transform.buffer)

            // Bind camera data
            bindBuffer(1, buffer)


            // -- GEOMETRY PASS

            // Set up render configuration
            setCullMode(CullMode.BACK)
            setFrontFace(FaceWinding.CCW)

            // Set render target
            bindFramebuffer(geometryFramebuffer)
            clearFramebuffer()

            // Draw each renderer
            for (renderer in scene findAll Renderable::class)
                renderer.draw(this)

            copyFramebuffer(
                geometryFramebuffer, Int4(0, 0, 640, 480),
                //outputFramebuffer, Int4(0, 0, window?.width ?: 0, window?.height ?: 0),
                outputFramebuffer, Int4(0, 0, outputFramebuffer.width, outputFramebuffer.height),
                CopyFramebufferMask.COLOR_BUFFER_BIT,
                CopyFramebufferFilter.NEAREST
            )

        }.submit()

    }

}