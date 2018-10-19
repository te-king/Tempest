package wrappers.opengl

import org.lwjgl.opengl.GL45C.*
import org.lwjgl.system.CustomBuffer
import java.nio.*

abstract class Buffer {

    abstract val device: Device
    abstract val id: Int

    val size get() = glGetNamedBufferParameteri(id, GL_BUFFER_SIZE)
    val immutable get() = glGetNamedBufferParameteri(id, GL_BUFFER_IMMUTABLE_STORAGE) == GL_TRUE

    fun allocateImmutable(size: Long, flags: GLbitfield) = glNamedBufferStorage(id, size, flags)
    fun allocateImmutable(data: CustomBuffer<*>, flags: GLbitfield) = nglNamedBufferStorage(id, data.sizeof() * data.limit().toLong(), data.address(), flags)
    fun allocateImmutable(data: ByteBuffer, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: ShortBuffer, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: IntBuffer, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: FloatBuffer, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: DoubleBuffer, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: ShortArray, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: IntArray, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: FloatArray, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)
    fun allocateImmutable(data: DoubleArray, flags: GLbitfield) = glNamedBufferStorage(id, data, flags)

    fun allocateDynamic(size: Long, usage: BufferUsage) = glNamedBufferData(id, size, usage.native)
    fun allocateDynamic(data: ByteBuffer, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: ShortBuffer, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: IntBuffer, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: LongBuffer, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: FloatBuffer, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: DoubleBuffer, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: ShortArray, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: IntArray, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: LongArray, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: FloatArray, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)
    fun allocateDynamic(data: DoubleArray, usage: BufferUsage) = glNamedBufferData(id, data, usage.native)

    fun getSubData(offset: GLintptr, data: ByteBuffer) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: ShortBuffer) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: IntBuffer) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: LongBuffer) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: FloatBuffer) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: DoubleBuffer) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: ShortArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: IntArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: LongArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: FloatArray) = glGetNamedBufferSubData(id, offset, data)
    fun getSubData(offset: GLintptr, data: DoubleArray) = glGetNamedBufferSubData(id, offset, data)

    fun setSubData(offset: GLintptr, data: ByteBuffer) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: ShortBuffer) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: IntBuffer) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: LongBuffer) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: FloatBuffer) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: DoubleBuffer) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: ShortArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: IntArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: LongArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: FloatArray) = glNamedBufferSubData(id, offset, data)
    fun setSubData(offset: GLintptr, data: DoubleArray) = glNamedBufferSubData(id, offset, data)

    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: ByteBuffer) = glClearNamedBufferData(id, internalformat, format, type, data)
    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: ShortBuffer) = glClearNamedBufferData(id, internalformat, format, type, data)
    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: IntBuffer) = glClearNamedBufferData(id, internalformat, format, type, data)
    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: FloatBuffer) = glClearNamedBufferData(id, internalformat, format, type, data)
    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: ShortArray) = glClearNamedBufferData(id, internalformat, format, type, data)
    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: IntArray) = glClearNamedBufferData(id, internalformat, format, type, data)
    fun clearData(internalformat: GLenum, format: GLenum, type: GLenum, data: FloatArray) = glClearNamedBufferData(id, internalformat, format, type, data)

    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: ByteBuffer) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)
    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: ShortBuffer) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)
    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: IntBuffer) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)
    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: FloatBuffer) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)
    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: ShortArray) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)
    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: IntArray) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)
    fun clearSubData(internalformat: GLenum, offset: GLintptr, size: GLsizeiptr, format: GLenum, type: GLenum, data: FloatArray) = glClearNamedBufferSubData(id, internalformat, offset, size, format, type, data)

    fun delete() = glDeleteBuffers(id)

}