package engine.world.controllers

import engine.world.Scene
import engine.world.Updatable
import engine.world.components.Rasterizer
import extensions.findAll
import math.Int4
import opengl.*

class RenderPipeline(scene: Scene) : Controller(scene), Updatable {


    val target = device.defaultFramebuffer

    var primaryRasterizer: Rasterizer? = null


    private val rasterPassFramebuffer = device.framebuffer(
        FramebufferAttachment.Color0 to device.image2d(640, 480, RGB8),
        FramebufferAttachment.Color1 to device.image2d(640, 480, RGB8),
        FramebufferAttachment.Depth to device.image2d(640, 480, DEPTH24)
    )

    private val rasterPassState = DeviceState(writeFramebuffer = rasterPassFramebuffer)


    override fun update(delta: Float) {

        device.enqueue(rasterPassState) {

            primaryRasterizer?.apply { this@enqueue.rasterize() }

            copyFramebuffer(
                src = rasterPassFramebuffer,
                srcRect = Int4(0, 0, rasterPassFramebuffer.width, rasterPassFramebuffer.height),
                dst = target,
                dstRect = Int4(0, 0, 640, 480),
                mask = CopyFramebufferMask.ColorBuffer,
                filter = CopyFramebufferFilter.Nearest
            )

            clearFramebuffer()

        }

    }

}