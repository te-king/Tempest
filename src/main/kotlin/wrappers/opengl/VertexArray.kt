@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.opengl.GL45.*

open class VertexArray(val device: Device) {

    val id = glCreateVertexArrays()

    fun bindAttribute(attribindex: GLuint, bindingindex: GLuint) = glVertexArrayAttribBinding(id, attribindex, bindingindex)
    fun formatFloatAttribute(attribindex: GLuint, size: GLint, type: GLenum, normalized: GLboolean, relativeoffset: GLuint) = glVertexArrayAttribFormat(id, attribindex, size, type, normalized, relativeoffset)
    fun formatIntAttribute(attribindex: GLuint, size: GLint, type: GLenum, relativeoffset: GLuint) = glVertexArrayAttribIFormat(id, attribindex, size, type, relativeoffset)
    fun formatLongAttribute(attribindex: GLuint, size: GLint, type: GLenum, relativeoffset: GLuint) = glVertexArrayAttribLFormat(id, attribindex, size, type, relativeoffset)

    fun delete() = glDeleteVertexArrays(id)

}