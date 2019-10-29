package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class DepthFunc(val native: Int) {
    Never(GL_NEVER),
    Less(GL_LESS),
    Greater(GL_GREATER),
    Equal(GL_EQUAL),
    Always(GL_ALWAYS),
    LEqual(GL_LEQUAL),
    GEqual(GL_GEQUAL),
    NotEqual(GL_NOTEQUAL)
}
