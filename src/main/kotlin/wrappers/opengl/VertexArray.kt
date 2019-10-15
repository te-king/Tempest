@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

open class VertexArray(device: Device, val id: Int) : Device.DeviceResource(device) {

    fun bindAttribute(attribindex: Int, bindingindex: Int) = glVertexArrayAttribBinding(id, attribindex, bindingindex)
    fun formatFloatAttribute(attribindex: Int, size: Int, type: Int, normalized: Boolean, relativeoffset: Int) = glVertexArrayAttribFormat(id, attribindex, size, type, normalized, relativeoffset)
    fun formatIntAttribute(attribindex: Int, size: Int, type: Int, relativeoffset: Int) = glVertexArrayAttribIFormat(id, attribindex, size, type, relativeoffset)
    fun formatLongAttribute(attribindex: Int, size: Int, type: Int, relativeoffset: Int) = glVertexArrayAttribLFormat(id, attribindex, size, type, relativeoffset)

    override fun delete() = glDeleteVertexArrays(id)

}