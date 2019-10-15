package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class CopyFramebufferMask(val native: Int) {
    COLOR_BUFFER_BIT(GL_COLOR_BUFFER_BIT),
    DEPTH_BUFFER_BIT(GL_DEPTH_BUFFER_BIT),
    STENCIL_BUFFER_BIT(GL_STENCIL_BUFFER_BIT)
}