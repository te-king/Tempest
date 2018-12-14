package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

class Program(device: Device, val id: Int): Device.DeviceResource(device) {

    override fun delete() = glDeleteProgram(id)

}