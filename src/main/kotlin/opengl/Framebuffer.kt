package opengl

import engine.runtime.Client
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.lwjgl.opengl.GL45C.glDeleteFramebuffers

class Framebuffer(val device: Device, val id: Int, val textures: Map<FramebufferAttachment, Image<*, Texture2d>>) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteFramebuffers(id)
        }
    }


    val width get() = if (id == 0) 640 else textures.values.map(Image<*, Texture2d>::width).max() ?: 0

    val height get() = if (id == 0) 480 else textures.values.map(Image<*, Texture2d>::height).max() ?: 0

}

