@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.PointerBuffer
import org.lwjgl.opengl.GL45.*
import java.nio.IntBuffer

abstract class VertexArray {

    abstract val device: Device
    abstract val id: Int

    fun disableVertexArrayAttrib(index: GLuint) = glDisableVertexArrayAttrib(id, index)
    fun enableVertexArrayAttrib(index: GLuint) = glEnableVertexArrayAttrib(id, index)
    fun vertexArrayElementBuffer(buffer: Buffer?) = glVertexArrayElementBuffer(id, buffer?.id ?: 0)
    fun vertexArrayVertexBuffer(bindingindex: GLuint, buffer: Buffer, offset: GLintptr, stride: GLsizei) = glVertexArrayVertexBuffer(id, bindingindex, buffer.id, offset, stride)
    fun vertexArrayVertexBuffers(first: GLuint, buffers: IntBuffer, offsets: PointerBuffer, strides: IntBuffer) = glVertexArrayVertexBuffers(id, first, buffers, offsets, strides)
    fun vertexArrayVertexBuffers(first: GLuint, buffers: IntArray, offsets: PointerBuffer, strides: IntArray) = glVertexArrayVertexBuffers(id, first, buffers, offsets, strides)
    fun vertexArrayAttribBinding(attribindex: GLuint, bindingindex: GLuint) = glVertexArrayAttribBinding(id, attribindex, bindingindex)
    fun vertexArrayAttribFormat(attribindex: GLuint, size: GLint, type: GLenum, normalized: GLboolean, relativeoffset: GLuint) = glVertexArrayAttribFormat(id, attribindex, size, type, normalized, relativeoffset)
    fun vertexArrayAttribIFormat(attribindex: GLuint, size: GLint, type: GLenum, relativeoffset: GLuint) = glVertexArrayAttribIFormat(id, attribindex, size, type, relativeoffset)
    fun vertexArrayAttribLFormat(attribindex: GLuint, size: GLint, type: GLenum, relativeoffset: GLuint) = glVertexArrayAttribLFormat(id, attribindex, size, type, relativeoffset)
    fun vertexArrayBindingDivisor(bindingindex: GLuint, divisor: GLuint) = glVertexArrayBindingDivisor(id, bindingindex, divisor)

    fun delete() = glDeleteVertexArrays(id)

}