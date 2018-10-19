package wrappers.opengl

import org.lwjgl.opengl.GL45.*

enum class FaceWinding(val native: Int) {
    CCW(GL_CCW),
    CW(GL_CW)
}
