package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class DepthFunc(val native: Int) {
    NEVER(GL_NEVER),
    LESS(GL_LESS),
    GREATER(GL_GREATER),
    EQUAL(GL_EQUAL),
    ALWAYS(GL_ALWAYS),
    LEQUAL(GL_LEQUAL),
    GEQUAL(GL_GEQUAL),
    NOTEQUAL(GL_NOTEQUAL)
}
