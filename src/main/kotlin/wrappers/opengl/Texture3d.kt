package wrappers.opengl

import org.lwjgl.opengl.GL45C.*
import java.nio.*

abstract class Texture3d(device: Device) : Texture(device, GL_TEXTURE_3D) {

    fun allocate(levels: GLsizei, internalformat: TextureFormat, width: GLsizei, height: GLsizei, depth: GLsizei) = glTextureStorage3D(id, levels, internalformat.native, width, height, depth)


    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)


}