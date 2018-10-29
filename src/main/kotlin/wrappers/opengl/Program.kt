package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

abstract class Program {

    abstract val device: Device
    abstract val id: Int

    val infoLog get() = glGetProgramInfoLog(id)

    fun delete() = glDeleteProgram(id)

}