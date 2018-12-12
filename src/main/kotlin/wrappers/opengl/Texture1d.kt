package wrappers.opengl

import org.lwjgl.opengl.GL45C.*
import java.nio.*

open class Texture1d(device: Device) : Texture(device, GL_TEXTURE_1D) {

    fun allocate(levels: GLsizei, internalformat: TextureFormat, width: GLsizei) = glTextureStorage1D(id, levels, internalformat.native, width)

    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)

}