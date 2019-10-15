package wrappers.opengl

import org.lwjgl.opengl.GL46C.glTextureSubImage1D
import java.nio.*

class Texture1d(device: Device, id: Int) : Texture(device, id) {

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)


    operator fun get(index: Int) = Image1d(this, index)

}

