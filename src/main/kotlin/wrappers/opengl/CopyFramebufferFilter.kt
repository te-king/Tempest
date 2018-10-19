package wrappers.opengl

import org.lwjgl.opengl.GL45.*

enum class CopyFramebufferFilter(val native: Int) {
    NEAREST(GL_NEAREST),
    LINEAR(GL_LINEAR)
}