package opengl

import org.lwjgl.opengl.GL46C.*

sealed class TextureFormat(val native: Int)
object R8 : TextureFormat(GL_R8)
object R16 : TextureFormat(GL_R16)
object RG8 : TextureFormat(GL_RG8)
object RG16 : TextureFormat(GL_RG16)
object RGB8 : TextureFormat(GL_RGB8)
object RGB16 : TextureFormat(GL_RGB16)
object RGBA8 : TextureFormat(GL_RGBA8)
object RGBA16 : TextureFormat(GL_RGBA16)
object R8SNORM : TextureFormat(GL_R8_SNORM)
object R16SNORM : TextureFormat(GL_R16_SNORM)
object RG8SNORM : TextureFormat(GL_RG8_SNORM)
object RG16SNORM : TextureFormat(GL_RG16_SNORM)
object RGB8SNORM : TextureFormat(GL_RGB8_SNORM)
object RGB16SNORM : TextureFormat(GL_RGB16_SNORM)
object RGBA8SNORM : TextureFormat(GL_RGBA8_SNORM)
object RGBA16SNORM : TextureFormat(GL_RGBA16_SNORM)
object RGBA32F : TextureFormat(GL_RGBA32F)
object DEPTH16 : TextureFormat(GL_DEPTH_COMPONENT16)
object DEPTH24 : TextureFormat(GL_DEPTH_COMPONENT24)
object DEPTH32 : TextureFormat(GL_DEPTH_COMPONENT32)
object DEPTH24STENCIL8 : TextureFormat(GL_DEPTH24_STENCIL8)
object STENCIL8 : TextureFormat(GL_STENCIL_INDEX8)


