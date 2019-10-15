package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class ProgramType(val native: Int, val bit: Int) {
    VERTEX(GL_VERTEX_SHADER, GL_VERTEX_SHADER_BIT),
    FRAGMENT(GL_FRAGMENT_SHADER, GL_FRAGMENT_SHADER_BIT),
    COMPUTE(GL_COMPUTE_SHADER, GL_COMPUTE_SHADER_BIT)
}

