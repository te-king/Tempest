package engine.world.controllers

import engine.world.*
import engine.world.components.*
import extensions.sizeOf
import math.*
import opengl.*

class Renderer(scene: Scene) : Controller(scene), Updatable {

    private val graphicsContext = controller<GraphicsContext>()
    private val renderables = components<Renderable>()


    var camera: Camera? = null

    var target = graphicsContext.device.defaultFramebuffer


    private val buffer = graphicsContext.device.buffer(
        sizeOf(
            Float::class,   // delta
            Double::class,  // uptime,
            Int4::class     // window position
        ),
        UniformBuffer, DynamicStorage
    )

    private val framebuffer = graphicsContext.device.framebuffer(
        FramebufferAttachment.Color0 to graphicsContext.device.image2d(640, 480, RGB8),
        FramebufferAttachment.Color1 to graphicsContext.device.image2d(640, 480, RGB8),
        FramebufferAttachment.Depth to graphicsContext.device.image2d(640, 480, DEPTH24)
    )

    private val state = DeviceState(writeFramebuffer = framebuffer)


    override fun update(delta: Float) {

        // Rasterize scene into initial raster framebuffer
        graphicsContext.device.enqueue(state) {

            // Update and bind general info buffer
            bindUniformBuffer(0, buffer)

            clearFramebuffer()
            camera?.attach(this)
            renderables.forEach { it.draw(this) }

        }

        // Copy backbuffer to window
        graphicsContext.device.enqueue(state) {
            copyFramebuffer(
                src = framebuffer,
                srcRect = Int4(0, 0, framebuffer.width, framebuffer.height),
                dst = target,
                dstRect = Int4(0, 0, target.width, target.height),
                mask = CopyFramebufferMask.ColorBuffer,
                filter = CopyFramebufferFilter.Nearest
            )
        }

    }


    interface Renderable {
        fun draw(buffer: CommandBuffer)
    }

}