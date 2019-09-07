package wrappers.opengl

import engine.runtime.Client
import org.lwjgl.opengl.GL45C.*

class Framebuffer(device: Device, val id: Int, private val textures: Map<Int, Image2d>): Device.DeviceResource(device) {


    companion object {
        val default = Framebuffer(Client.device, 0, mapOf())
    }


    init {
        for (it in textures) glNamedFramebufferTexture(id, it.key, it.value.texture.id, it.value.index)
    }


    // TODO: Update
    val width get() = if (id == 0) Client.window.width else textures.values.map(Image2d::width).max() ?: 0

    // TODO: Update
    val height get() = if (id == 0) Client.window.height else textures.values.map(Image2d::height).max() ?: 0


    /*
    // Native Functions
    fun namedFramebufferRenderbuffer(attachment: GLenum, renderbuffertarget: GLenum, renderbuffer: GLuint) = glNamedFramebufferRenderbuffer(id, attachment, renderbuffertarget, renderbuffer)
    fun namedFramebufferParameteri(pname: GLenum, param: GLint) = glNamedFramebufferParameteri(id, pname, param)
    fun namedFramebufferTexture(attachment: GLenum, texture: Texture2d?, level: GLint) = glNamedFramebufferTexture(id, attachment, texture?.id ?: 0, level)
    fun namedFramebufferTextureLayer(attachment: GLenum, texture: GLuint, level: GLint, layer: GLint) = glNamedFramebufferTextureLayer(id, attachment, texture, level, layer)
    fun namedFramebufferDrawBuffer(buf: GLenum) = glNamedFramebufferDrawBuffer(id, buf)
    fun namedFramebufferReadBuffer(src: GLenum) = glNamedFramebufferReadBuffer(id, src)
    //fun clearNamedFramebufferiv(buffer: GLenum, drawbuffer: GLint, value: constructs.Pointer<GLint>) = nglClearNamedFramebufferiv(id, buffer, drawbuffer, value.address)
    //fun clearNamedFramebufferuiv(buffer: GLenum, drawbuffer: GLint, value: constructs.Pointer<GLuint>) = nglClearNamedFramebufferuiv(id, buffer, drawbuffer, value.address)
    //fun clearNamedFramebufferfv(buffer: GLenum, drawbuffer: GLint, value: constructs.Pointer<GLfloat>) = nglClearNamedFramebufferfv(id, buffer, drawbuffer, value.address)
    //fun clearNamedFramebufferfi(buffer: GLenum, drawbuffer: GLint, depth: GLfloat, stencil: GLint) = glClearNamedFramebufferfi(id, buffer, drawbuffer, depth, stencil)
    //fun checkNamedFramebufferStatus(target: GLenum) = glCheckNamedFramebufferStatus(id, target)
    //fun getNamedFramebufferParameteriv(pname: FramebufferParameter, param: constructs.Pointer<GLint>) = nglGetNamedFramebufferParameteriv(id, pname.native, param.address)
    //fun getNamedFramebufferAttachmentParameteriv(attachment: GLenum, pname: GLenum, params: constructs.Pointer<GLint>) = nglGetNamedFramebufferAttachmentParameteriv(id, attachment, pname, params.address)


    // LWJGL Wrapped Functions
    fun namedFramebufferDrawBuffers(bufs: IntBuffer) = glNamedFramebufferDrawBuffers(id, bufs)
    fun namedFramebufferDrawBuffers(bufs: IntArray) = glNamedFramebufferDrawBuffers(id, bufs)
    //invalidateNamedFramebufferData
    //invalidateNamedFramebufferSubData
    //clearNamedFramebufferiv
    //clearNamedFramebufferuiv
    //clearNamedFramebufferfv
    */

    override fun delete() = glDeleteFramebuffers(id)

}