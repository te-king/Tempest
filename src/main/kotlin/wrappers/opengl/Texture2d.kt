package wrappers.opengl

import org.lwjgl.opengl.GL46C.*
import java.nio.*

class Texture2d(device: Device, id: Int) : Texture(device, id) {

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ByteBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ShortBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: IntBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: FloatBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ShortArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: IntArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: FloatArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: DoubleArray) = glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)


    operator fun get(index: Int) = Image2d(this, index)

}