package engine.world.components

import engine.gui.components.*
import engine.world.*
import engine.world.controllers.Window
import opengl.*

class Gui(node: Node) : Component(node), Updatable {

    private val window = controller<Window>()
    private val device = window.device

    var root: Element? = null

    var output: Framebuffer? = null


    private val guiFramebuffer = device.framebuffer(
        FramebufferAttachment.Color0 to device.image2d(640, 480, RGBA8),
        FramebufferAttachment.Stencil to device.image2d(640, 480, STENCIL8)
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