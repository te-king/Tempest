package wrappers.opengl

import org.lwjgl.opengl.GL45C.*
import java.nio.IntBuffer

class Framebuffer(device: Device, val id: Int): Device.DeviceResource(device) {

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