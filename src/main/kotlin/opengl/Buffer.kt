package opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*


class Buffer<T : BufferTarget, S : StorageKind>(val device: Device, val id: Int, val size: Long, val target: T, val storage: S) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteBuffers(id)
        }
    }

}

fun Buffer<*, *>.setSubData(offset: Long, data: ShortArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

fun Buffer<*, *>.setSubData(offset: Long, data: IntArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

fun Buffer<*, *>.setSubData(offset: Long, data: LongArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

fun Buffer<*, *>.setSubData(offset: Long, data: FloatArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }

fun Buffer<*, *>.setSubData(offset: Long, data: DoubleArray) =
        GlobalScope.launch(device.context) {
            glNamedBufferSubData(id, offset, data)
        }