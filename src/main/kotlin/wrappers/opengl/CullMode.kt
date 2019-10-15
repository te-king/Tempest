package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class CullMode(val native: Int) {
    FRONT(GL_FRONT),
    BACK(GL_BACK),
    FRONT_AND_BACK(GL_FRONT_AND_BACK)
}