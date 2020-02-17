package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

sealed class TextureKind(val native: Int)
object Texture1d : TextureKind(GL_TEXTURE_1D)
object Texture2d : TextureKind(GL_TEXTURE_2D)