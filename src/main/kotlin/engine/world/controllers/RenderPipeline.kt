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

    var primaryShadowCaster: Rasterizer? = null


    private val rasterPassFramebuffer = device.framebuffer(
        FramebufferAttachment.Color0 to device.image2d(640, 480, RGB8),
        FramebufferAttachment.Color1 to device.image2d(640, 480, RGB8),
        FramebufferAttachment.Depth to device.image2d(640, 480, DEPTH24)
    )

    private val rasterPassState = DeviceState(writeFramebuffer = rasterPassFramebuffer)


    private val shadowPassFramebuffer = device.framebuffer(
        FramebufferAttachment.Depth to device.image2d(1024, 1024, DEPTH24)
    )

    private val shadowPassState = DeviceState(writeFramebuffer = shadowPassFramebuffer)


    override fun update(delta: Float) {

        // Rasterize scene into initial raster framebuffer
        primaryRasterizer?.apply {
            device.enqueue(rasterPassState) {
                clearFramebuffer()
                rasterize()
            }
        }

        primaryShadowCaster?.apply {
            device.enqueue(shadowPassState) {
                clearFramebuffer()
                rasterize()
            }
        }


        device.enqueue(rasterPassState) {
            copyFramebuffer(
                src = rasterPassFramebuffer,
                srcRect = Int4(0, 0, rasterPassFramebuffer.width, rasterPassFramebuffer.height),
                dst = target,
                dstRect = Int4(0, 0, 640, 480),
                mask = CopyFramebufferMask.ColorBuffer,
                filter = CopyFramebufferFilter.Nearest
            )
        }


    }

}