package opengl

import org.lwjgl.opengl.GL46C.*

enum class CullMode(val native: Int) {
    Front(GL_FRONT),
    Back(GL_BACK),
    FrontAndBack(GL_FRONT_AND_BACK)
}