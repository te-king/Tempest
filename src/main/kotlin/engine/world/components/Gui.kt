package engine.world.components

import engine.gui.components.*
import engine.world.*
import org.lwjgl.opengl.GL45C.*
import wrappers.opengl.*

class Gui(node: Node) : Component(node), Updatable {

    var root: Element? = null

    var output: Framebuffer? = null


    private val guiFramebuffer = device.framebuffer(
        GL_COLOR_ATTACHMENT0 to device.image2d(TextureFormat.RGBA8, 640, 480),
        GL_STENCIL_ATTACHMENT to device.image2d(TextureFormat.STENCIL8, 640, 480)
    )

    private val state = DeviceState(
        guiFramebuffer,
        cull = false,
        blend = true,
        depth = false,
        stencil = true
    )


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