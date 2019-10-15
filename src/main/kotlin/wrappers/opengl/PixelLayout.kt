package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class PixelLayout(val native: Int) {
    RED(GL_RED),
    RG(GL_RG),
    RGB(GL_RGB),
    BGR(GL_BGR),
    RGBA(GL_RGBA),
    BGRA(GL_BGRA),
    DEPTH_COMPONENT(GL_DEPTH_COMPONENT),
    STENCIL(GL_STENCIL_INDEX)
}