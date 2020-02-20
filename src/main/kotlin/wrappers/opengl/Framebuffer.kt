package wrappers.opengl

import engine.runtime.Client
import kotlinx.coroutines.*
import org.lwjgl.opengl.GL45C.*

class Framebuffer(val device: Device, val id: Int, val textures: Map<Int, Image<*, Texture2d>>) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteFramebuffers(id)
        }
    }


    val width get() = if (id == 0) Client.window.width else textures.values.map(Image<*, Texture2d>::width).max() ?: 0

    val height get() = if (id == 0) Client.window.height else textures.values.map(Image<*, Texture2d>::height).max() ?: 0

}