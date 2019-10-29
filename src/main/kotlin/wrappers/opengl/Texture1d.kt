package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*
import java.nio.*

class Texture1d(device: Device, id: Int, levels: Int, internalFormat: TextureFormat, val width: Int) : Texture(device, id, levels, internalFormat) {

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: ByteBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: ShortBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: IntBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: FloatBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: ShortArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: IntArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: FloatArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, width: Int, format: PixelLayout, type: PixelType, pixels: DoubleArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage1D(id, level, xoffset, width, format.native, type.native, pixels)
        }


    operator fun get(index: Int) = Image1d(this@Texture1d, index)

}

