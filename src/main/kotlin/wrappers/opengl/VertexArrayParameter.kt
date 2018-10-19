package wrappers.opengl

import org.lwjgl.opengl.GL45.*

enum class VertexArrayParameter(val native: Int) {
    ELEMENT_ARRAY_BUFFER_BINDING(GL_ELEMENT_ARRAY_BUFFER_BINDING)
}