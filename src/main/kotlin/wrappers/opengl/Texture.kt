package wrappers.opengl

import org.lwjgl.opengl.ARBBindlessTexture.*
import org.lwjgl.opengl.GL45C.*

abstract class Texture(device: Device, val id: Int): Device.DeviceResource(device) {

    val handle get() = glGetTextureHandleARB(id)

    var resident: Boolean
        get() = glIsTextureHandleResidentARB(handle)
        set(value) = if (value) glMakeTextureHandleResidentARB(handle) else glMakeTextureHandleNonResidentARB(handle)


    override fun delete() = glDeleteTextures(id)

}




