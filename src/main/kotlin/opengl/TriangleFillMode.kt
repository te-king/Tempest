package opengl

import org.lwjgl.opengl.GL46C.*

enum class TriangleFillMode(val native: Int) {
    Point(GL_POINT),
    Line(GL_LINE),
    Fill(GL_FILL),
}
