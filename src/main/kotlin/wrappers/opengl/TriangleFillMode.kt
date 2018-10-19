package wrappers.opengl

import org.lwjgl.opengl.GL45.*

enum class TriangleFillMode(val native: Int) {
    POINT(GL_POINT),
    LINE(GL_LINE),
    FILL(GL_FILL),
}
