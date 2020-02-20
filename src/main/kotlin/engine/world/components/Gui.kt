package engine.world.components

import engine.gui.components.*
import engine.world.*
import org.lwjgl.opengl.GL45C.*
import opengl.*

class Gui(node: Node) : Component(node), Updatable {

    var root: Element? = null

    var output: Framebuffer? = null


    private val guiFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.image2d(640, 480, RGBA8),
        GL_STENCIL_ATTACHMENT to device.image2d(640, 480, STENCIL8)
    )

    private val state = DeviceState(writeFramebuffer = guiFramebuffer, stencilFunc = Unit)


    override fun update(delta: Float) {

        val root = root ?: return
        val output = output ?: return

        device.enqueue(state) {

            clearFramebuffer()

            paint(root)

//            copyFramebuffer(
//                src = guiFramebuffer,
//                dst = output,
//                mask = CopyFramebufferMask.ColorBuffer,
//                filter = CopyFramebufferFilter.NEAREST
//            )

        }

    }

}