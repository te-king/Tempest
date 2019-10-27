package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*
import java.nio.*

class Texture2d(device: Device, id: Int) : Texture(device, id) {

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ByteBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ShortBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: IntBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: FloatBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: DoubleBuffer) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ShortArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: IntArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: FloatArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }

    fun setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: DoubleArray) =
        GlobalScope.launch(device.context) {
            glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
        }


    operator fun get(index: Int) = Image2d(this, index)

}