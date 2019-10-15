package engine.world.components

import engine.gui.components.*
import engine.world.*
import math.*
import org.lwjgl.opengl.GL45C.*
import wrappers.opengl.*

class Gui(node: Node) : Component(node), Updatable {

    var root: Element? = null

    var output: Framebuffer? = null


    private val guiFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.image2d(TextureFormat.RGBA8, 640, 480),
        GL_STENCIL_ATTACHMENT to device.image2d(TextureFormat.STENCIL8, 640, 480)
    )


    override fun update(delta: Float) {

        val root = root ?: return
        val output = output ?: return

        device.enqueue {

            bindFramebuffer(guiFramebuffer)
            clearFramebuffer()

            root.let(this::paint)

            copyFramebuffer(
                guiFramebuffer, Int4(0, 0, 640, 480),
                output, Int4(0, 0, output.width, output.height),
                CopyFramebufferMask.COLOR_BUFFER_BIT, CopyFramebufferFilter.NEAREST
            )

        }

    }

}