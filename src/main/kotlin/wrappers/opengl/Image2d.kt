package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Image2d(override val texture: Texture2d, val index: Int) : Image() {

    val width
        get() = runBlocking(texture.device.context) {
            glGetTextureLevelParameteri(texture.id, index, GL_TEXTURE_WIDTH)
        }

    val height
        get() = runBlocking(texture.device.context) {
            glGetTextureLevelParameteri(texture.id, index, GL_TEXTURE_HEIGHT)
        }

}