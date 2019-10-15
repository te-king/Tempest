package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class PrimitiveType(val native: Int) {
    TRIANGLES(GL_TRIANGLES)
}