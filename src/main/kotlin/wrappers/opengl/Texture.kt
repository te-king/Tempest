package wrappers.opengl

import org.lwjgl.opengl.ARBBindlessTexture.*
import org.lwjgl.opengl.GL45C.*

open class Texture(val device: Device, val target: Int) {

    val id = glCreateTextures(target)


    val handle get() = glGetTextureHandleARB(id)

    var resident: Boolean
        get() = glIsTextureHandleResidentARB(handle)
        set(value) = if (value) glMakeTextureHandleResidentARB(handle) else glMakeTextureHandleNonResidentARB(handle)


    fun delete() = glDeleteTextures(id)

}

