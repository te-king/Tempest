package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class CopyFramebufferFilter(val native: Int) {
    Nearest(GL_NEAREST),
    Linear(GL_LINEAR)
}