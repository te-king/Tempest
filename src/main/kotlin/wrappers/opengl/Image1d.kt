package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

class Image1d(override val texture: Texture1d, val index: Int): Image() {

    val width get() = glGetTextureLevelParameteri(texture.id, index, GL_TEXTURE_WIDTH)

}