package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Buffer(val device: Device, val id: Int) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteBuffers(id)
        }
    }


    val size
        get() = runBlocking(device.context) {
            glGetNamedBufferParameteri(id, GL_BUFFER_SIZE)
        }

    val immutable
        get() = runBlocking(device.context) {
            glGetNamedBufferParameteri(id, GL_BUFFER_IMMUTABLE_STORAGE) == GL_TRUE
        }

    fun getSubData(offset: Long, data: ShortArray) =
        runBlocking(device.context) {
            glGetNamedBufferSubData(id, offset, data)
        }

    fun getSubData(offset: Long, data: IntArray) =
        runBlocking(device.context) {
            glGetNamedBufferSubData(id, offset, data)
        }

    fun getSubData(offset: Long, data: LongArray) =
        runBlocking(device.context) {
            glGetNamedBufferSubData(id, offset, data)
        }

    fun getSubData(offset: Long, data: FloatArray) =
        runBlocking(device.context) {
            glGetNamedBufferSubData(id, offset, data)
        }

    fun getSubData(offset: Long, data: DoubleArray) =
        runBlocking(device.context) {
            glGetNamedBufferSubData(id, offset, data)
        }


    fun setSubData(offset: Long, data: ShortArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

    fun setSubData(offset: Long, data: IntArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

    fun setSubData(offset: Long, data: LongArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

    fun setSubData(offset: Long, data: FloatArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

    fun setSubData(offset: Long, data: DoubleArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

    fun invalidate() =
        GlobalScope.launch(device.context) {
            glInvalidateBufferData(id)
        }

}