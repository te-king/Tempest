package wrappers.opengl

import org.lwjgl.opengl.GL45.*

enum class PrimitiveType(val native: Int) {
    TRIANGLES(GL_TRIANGLES)
}