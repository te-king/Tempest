package wrappers.opengl

import org.lwjgl.opengl.GL45C.*
import java.nio.*

abstract class Texture2d(device: Device) : Texture(device, GL_TEXTURE_2D) {

    fun allocate(levels: GLsizei, internalformat: TextureFormat, width: GLsizei, height: GLsizei) = glTextureStorage2D(id, levels, internalformat.native, width, height)


    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)

}