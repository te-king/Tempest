package engine.world.components

import engine.gui.Element
import engine.world.Node
import engine.world.Updatable
import math.Int4
import org.lwjgl.opengl.GL45C.*
import wrappers.opengl.CopyFramebufferFilter
import wrappers.opengl.CopyFramebufferMask
import wrappers.opengl.Framebuffer
import wrappers.opengl.TextureFormat

class Gui(node: Node) : Component(node), Updatable {

    var root: Element? = null

    var outputFramebuffer = Framebuffer.default


    private val guiFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.image2d(TextureFormat.RGBA8, 640, 480),
        GL_STENCIL_ATTACHMENT to device.image2d(TextureFormat.STENCIL8, 640, 480)
    )


    override fun update(delta: Float) {

        device.commandBuffer().apply {

            bindFramebuffer(guiFramebuffer)
            clearFramebuffer()

            root?.let(this::paint)

            copyFramebuffer(
                guiFramebuffer, Int4(0, 0, 640, 480),
                //outputFramebuffer, Int4(0, 0, window?.width ?: 0, window?.height ?: 0),
                outputFramebuffer, Int4(0, 0, outputFramebuffer.width, outputFramebuffer.height), CopyFramebufferMask.COLOR_BUFFER_BIT, CopyFramebufferFilter.NEAREST
            )

        }.submit()

    }

}