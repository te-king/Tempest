package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class CopyFramebufferMask(val native: Int) {
    ColorBuffer(GL_COLOR_BUFFER_BIT),
    DepthBuffer(GL_DEPTH_BUFFER_BIT),
    StencilBuffer(GL_STENCIL_BUFFER_BIT)
}