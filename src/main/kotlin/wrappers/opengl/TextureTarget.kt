package wrappers.opengl

import org.lwjgl.opengl.GL45.*

sealed class TextureTarget(val native: Int) {
    object TEXTURE_1D: TextureTarget(GL_TEXTURE_1D)
    object TEXTURE_2D: TextureTarget(GL_TEXTURE_2D)
    object TEXTURE_3D: TextureTarget(GL_TEXTURE_3D)
}