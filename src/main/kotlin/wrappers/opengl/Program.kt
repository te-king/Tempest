package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

open class Program(val device: Device, type: ProgramType, source: String) {

    val id = glCreateShaderProgramv(type.native, source)

    val infoLog get() = glGetProgramInfoLog(id)

    fun delete() = glDeleteProgram(id)

}