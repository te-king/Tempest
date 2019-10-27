package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Image1d(override val texture: Texture1d, val index: Int) : Image() {

    val width
        get() = runBlocking(texture.device.context) {
            glGetTextureLevelParameteri(texture.id, index, GL_TEXTURE_WIDTH)
        }

}