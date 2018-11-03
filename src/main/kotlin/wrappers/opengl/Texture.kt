package wrappers.opengl

import org.lwjgl.opengl.ARBBindlessTexture.*
import org.lwjgl.opengl.GL45C.*

abstract class Texture {

    abstract val device: Device
    abstract val id: Int
    abstract val target: Int


    val handle get() = glGetTextureHandleARB(id)

    var resident: Boolean
        get() = glIsTextureHandleResidentARB(handle)
        set(value) = if (value) glMakeTextureHandleResidentARB(handle) else glMakeTextureHandleNonResidentARB(handle)


    fun delete() = glDeleteTextures(id)

}

