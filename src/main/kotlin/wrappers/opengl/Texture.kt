package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.ARBBindlessTexture.*
import org.lwjgl.opengl.GL46C.*

abstract class Texture(val device: Device, val id: Int, val levels: Int, val internalFormat: TextureFormat) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteTextures(id)
        }
    }


    val handle
        get() = runBlocking(device.context) {
            glGetTextureHandleARB(id)
        }

    var resident: Boolean
        get() = runBlocking(device.context) {
            glIsTextureHandleResidentARB(handle)
        }
        set(value) = runBlocking(device.context) {
            if (value) glMakeTextureHandleResidentARB(handle)
            else glMakeTextureHandleNonResidentARB(handle)
        }

}




