package wrappers.opengl

import org.lwjgl.opengl.ARBBindlessTexture.*
import org.lwjgl.opengl.GL45C.*
import java.nio.*


abstract class Texture {

    abstract val device: Device
    abstract val id: Int
    abstract val target: TextureTarget


    val handle get() = glGetTextureHandleARB(id)

    var resident: Boolean
        get() = glIsTextureHandleResidentARB(handle)
        set(value) = if (value) glMakeTextureHandleResidentARB(handle) else glMakeTextureHandleNonResidentARB(handle)


    fun allocate1d(levels: GLsizei, internalformat: TextureFormat, width: GLsizei) = glTextureStorage1D(id, levels, internalformat.native, width)
    fun allocate2d(levels: GLsizei, internalformat: TextureFormat, width: GLsizei, height: GLsizei) = glTextureStorage2D(id, levels, internalformat.native, width, height)
    fun allocate3d(levels: GLsizei, internalformat: TextureFormat, width: GLsizei, height: GLsizei, depth: GLsizei) = glTextureStorage3D(id, levels, internalformat.native, width, height, depth)
    fun allocate2dMultisample(samples: GLsizei, internalformat: GLenum, width: GLsizei, height: GLsizei, fixedsamplelocations: GLboolean) = glTextureStorage2DMultisample(id, samples, internalformat, width, height, fixedsamplelocations)
    fun allocate3dMultisample(samples: GLsizei, internalformat: GLenum, width: GLsizei, height: GLsizei, depth: GLsizei, fixedsamplelocations: GLboolean) = glTextureStorage3DMultisample(id, samples, internalformat, width, height, depth, fixedsamplelocations)

    fun delete() = glDeleteTextures(id)


    operator fun get(level: GLint) = Image(level)


    inner class Image(val level: GLint) {

        val width get() = glGetTextureLevelParameteri(id, level, GL_TEXTURE_WIDTH)
        val height get() = glGetTextureLevelParameteri(id, level, GL_TEXTURE_HEIGHT)
        val depth get() = glGetTextureLevelParameteri(id, level, GL_TEXTURE_DEPTH)
        
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        fun setSubImage1d(xoffset: GLint, width: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)

        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        fun setSubImage2d(xoffset: GLint, yoffset: GLint, width: GLsizei, height: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)

        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)
        fun setSubImage3d(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage3D(id, level, xoffset, yoffset, zoffset, width, height, depth, format.native, type.native, pixels)

        fun clearImage(format: GLenum, type: GLenum, data: ByteBuffer) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: ShortBuffer) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: IntBuffer) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: FloatBuffer) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: DoubleBuffer) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: ShortArray) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: IntArray) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: FloatArray) = glClearTexImage(id, level, format, type, data)
        fun clearImage(format: GLenum, type: GLenum, data: DoubleArray) = glClearTexImage(id, level, format, type, data)

        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: ByteBuffer) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: ShortBuffer) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: IntBuffer) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: FloatBuffer) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: DoubleBuffer) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: ShortArray) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: IntArray) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: FloatArray) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)
        fun clearSubImage(xoffset: GLint, yoffset: GLint, zoffset: GLint, width: GLsizei, height: GLsizei, depth: GLsizei, format: GLenum, type: GLenum, data: DoubleArray) = glClearTexSubImage(id, level, xoffset, yoffset, zoffset, width, height, depth, format, type, data)

    }

}