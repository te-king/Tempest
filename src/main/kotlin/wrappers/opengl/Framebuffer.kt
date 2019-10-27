package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL45C.*

open class Framebuffer(val device: Device, val id: Int, val textures: Map<Int, Image2d>) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteFramebuffers(id)
        }
    }


    open val width get() = textures.values.map(Image2d::width).max() ?: 0

    open val height get() = textures.values.map(Image2d::height).max() ?: 0

}