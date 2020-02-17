package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.ARBBindlessTexture.*
import org.lwjgl.opengl.GL46C.*
import java.nio.ByteBuffer
import java.nio.ShortBuffer


class Texture<F : TextureFormat, K : TextureKind>(val device: Device, val id: Int, val levels: Int, val width: Int, val height: Int, val depth: Int, val internalFormat: F, val kind: K) {

    operator fun get(index: Int) = Image(this, index)

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteTextures(id)
        }
    }

}

val Texture<*, *>.handle
    get() = runBlocking(device.context) {
        glGetTextureHandleARB(id)
    }

var Texture<*, *>.resident: Boolean
    get() = runBlocking(device.context) {
        glIsTextureHandleResidentARB(handle)
    }
    set(value) = runBlocking(device.context) {
        if (value) glMakeTextureHandleResidentARB(handle)
        else glMakeTextureHandleNonResidentARB(handle)
    }


fun Texture<*, Texture2d>.setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ByteBuffer) =
    GlobalScope.launch(device.context) {
        glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    }

fun Texture<*, Texture2d>.setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ShortBuffer) =
    GlobalScope.launch(device.context) {
        glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    }

fun Texture<*, Texture2d>.setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: ShortArray) =
    GlobalScope.launch(device.context) {
        glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    }

fun Texture<*, Texture2d>.setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: IntArray) =
    GlobalScope.launch(device.context) {
        glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    }

fun Texture<*, Texture2d>.setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: FloatArray) =
    GlobalScope.launch(device.context) {
        glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    }

fun Texture<*, Texture2d>.setSubImage(level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: PixelLayout, type: PixelType, pixels: DoubleArray) =
    GlobalScope.launch(device.context) {
        glTextureSubImage2D(id, level, xoffset, yoffset, width, height, format.native, type.native, pixels)
    }

