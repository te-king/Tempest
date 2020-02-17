package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

sealed class ProgramKind(val native: Int, val bit: Int)
object VertexProgram: ProgramKind(GL_VERTEX_SHADER, GL_VERTEX_SHADER_BIT)
object FragmentProgram: ProgramKind(GL_FRAGMENT_SHADER, GL_FRAGMENT_SHADER_BIT)
object ComputeProgram: ProgramKind(GL_COMPUTE_SHADER, GL_COMPUTE_SHADER_BIT)


