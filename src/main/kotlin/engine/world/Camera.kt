package engine.world

import math.Int4
import wrappers.glfw.Window
import math.ProjectionMatrix
import org.lwjgl.opengl.GL45C.*
import wrappers.opengl.*

class Camera (node: Node) : Component(node), Updatable {

    private val transform = node add Transform::class


    //
    val buffer = device.buffer(Int.SIZE_BYTES.toLong() * 16, GL_DYNAMIC_STORAGE_BIT)


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


    var window: Window? = null


    // Projection Matrix
    private var projectionInvalid = true

    var projectionMatrix = ProjectionMatrix.identity
        get() {
            if (projectionInvalid) validateProjection()
            return field
        }
        private set(value) {
            buffer.setSubData(0, value.matrix.data)
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
        GL_COLOR_ATTACHMENT0 to device.texture2d(1, TextureFormat.RGB8, 640, 480),
        GL_COLOR_ATTACHMENT1 to device.texture2d(1, TextureFormat.RGB8, 640, 480),
        GL_DEPTH_ATTACHMENT to device.texture2d(1, TextureFormat.DEPTH24, 640, 480)
    )

    private val illuminationFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.texture2d(1, TextureFormat.RGB8, 640, 480)
    )

/*
    private val geometryFramebuffer = device.framebuffer().apply {

        val colorTexture = device.texture2d()
        colorTexture.allocate(1, TextureFormat.RGB8, 640, 480)

        val normalTexture = device.texture2d()
        normalTexture.allocate(1, TextureFormat.RGB8, 640, 480)

        val depthTexture = device.texture2d()
        depthTexture.allocate(1, TextureFormat.DEPTH24, 640, 480)

        this.namedFramebufferTexture(GL_COLOR_ATTACHMENT0, colorTexture, 0)
        this.namedFramebufferTexture(GL_COLOR_ATTACHMENT1, normalTexture, 0)
        this.namedFramebufferTexture(GL_DEPTH_ATTACHMENT, depthTexture, 0)

    }

    private val illuminationFramebuffer = device.framebuffer().apply {

        val illuminationTexture = device.texture2d()
        illuminationTexture.allocate(1, TextureFormat.RGB8, 640, 480)

        this.namedFramebufferTexture(GL_COLOR_ATTACHMENT0, illuminationTexture, 0)

    }*/


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

            // Draw each meshRenderer
            for (meshRenderer in scene.mapNotNull { it get MeshRenderer::class })
                meshRenderer.draw(this)


            // -- ILLUMINATION PASS

            // Render both sides (not needed?)
            setCullMode(CullMode.FRONT_AND_BACK)

            // Set render target
            bindFramebuffer(illuminationFramebuffer)
            clearFramebuffer()

            // Draw each LightEmitter
            for (lightEmitter in scene.mapNotNull { it get LightEmitter::class })
                lightEmitter.draw(this)


            // -- GATHER PASS
            /*
            TODO: Implement gather pipeline, For the timebeing just use the final blit

            - max = Find max average light of frame

            -

            - bind fb 0

            - Shader:
                brightness = calculatedBrightness / max
                color = albedo * brightness


             */

            copyFramebuffer(
                geometryFramebuffer, Int4(0, 0, 640, 480),
                null, Int4(0, 0, window?.width ?: 0, window?.height ?: 0),
                CopyFramebufferMask.COLOR_BUFFER_BIT,
                CopyFramebufferFilter.NEAREST
            )

        }.submit()

        // Push the frame to the display
        window?.swapBuffers()

    }

}