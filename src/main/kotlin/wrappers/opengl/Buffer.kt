package wrappers.opengl

import org.lwjgl.opengl.GL43C
import org.lwjgl.opengl.GL45C.*
import org.lwjgl.system.CustomBuffer
import java.nio.*

class Buffer(device: Device, val id: Int): Device.DeviceResource(device) {

    val size get() = glGetNamedBufferParameteri(id, GL_BUFFER_SIZE)
    val immutable get() = glGetNamedBufferParameteri(id, GL_BUFFER_IMMUTABLE_STORAGE) == GL_TRUE

    fun getSubData(offset: GLintptr, data: ShortArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: IntArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: LongArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: FloatArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: DoubleArray) = glGetNamedBufferSubData(id, offset, data)

    fun setSubData(offset: GLintptr, data: ShortArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: IntArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: LongArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: FloatArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: DoubleArray) = glNamedBufferSubData(id, offset, data)

    fun invalidate() = glInvalidateBufferData(id)

    override fun delete() = glDeleteBuffers(id)

}