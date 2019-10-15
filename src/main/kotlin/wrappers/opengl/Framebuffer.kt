package wrappers.opengl

import engine.runtime.Client
import org.lwjgl.opengl.GL45C.glDeleteFramebuffers
import org.lwjgl.opengl.GL45C.glNamedFramebufferTexture

open class Framebuffer(device: Device, val id: Int, private val textures: Map<Int, Image2d>) : Device.DeviceResource(device) {


    companion object {
        val default = Framebuffer(Client.device, 0, mapOf())
    }


    init {
        for (it in textures) glNamedFramebufferTexture(id, it.key, it.value.texture.id, it.value.index)
    }


    open val width get() = textures.values.map(Image2d::width).max() ?: 0

    open val height get() = textures.values.map(Image2d::height).max() ?: 0


    override fun delete() = glDeleteFramebuffers(id)

}