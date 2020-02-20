package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class PrimitiveType(val native: Int) {
    Triangles(GL_TRIANGLES)
}