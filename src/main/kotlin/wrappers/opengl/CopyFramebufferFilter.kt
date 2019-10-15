package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class CopyFramebufferFilter(val native: Int) {
    NEAREST(GL_NEAREST),
    LINEAR(GL_LINEAR)
}