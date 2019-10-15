package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

class Buffer(device: Device, val id: Int) : Device.DeviceResource(device) {

    val size get() = glGetNamedBufferParameteri(id, GL_BUFFER_SIZE)
    val immutable get() = glGetNamedBufferParameteri(id, GL_BUFFER_IMMUTABLE_STORAGE) == GL_TRUE

    fun getSubData(offset: Long, data: ShortArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: Long, data: IntArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: Long, data: LongArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: Long, data: FloatArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: Long, data: DoubleArray) = glGetNamedBufferSubData(id, offset, data)

    fun setSubData(offset: Long, data: ShortArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: Long, data: IntArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: Long, data: LongArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: Long, data: FloatArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: Long, data: DoubleArray) = glNamedBufferSubData(id, offset, data)

    fun invalidate() = glInvalidateBufferData(id)

    override fun delete() = glDeleteBuffers(id)

}