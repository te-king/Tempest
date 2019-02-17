package wrappers.opengl

import org.lwjgl.opengl.GL45C.glDeleteProgram
import org.lwjgl.opengl.GL45C.glGetProgramInfoLog

class Program(device: Device, val id: Int): Device.DeviceResource(device) {

    val infoLog get() = glGetProgramInfoLog(id)

    override fun delete() = glDeleteProgram(id)

}