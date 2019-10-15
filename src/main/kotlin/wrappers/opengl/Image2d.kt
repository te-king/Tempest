package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

class Image2d(override val texture: Texture2d, val index: Int): Image() {

    val width get() = glGetTextureLevelParameteri(texture.id, index, GL_TEXTURE_WIDTH)
    val height get() = glGetTextureLevelParameteri(texture.id, index, GL_TEXTURE_HEIGHT)

}