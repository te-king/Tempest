@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

open class VertexArray(val device: Device, val id: Int) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteVertexArrays(id)
        }
    }


    fun bindAttribute(attribindex: Int, bindingindex: Int) =
        GlobalScope.launch(device.context) {
            glVertexArrayAttribBinding(id, attribindex, bindingindex)
        }

    fun formatFloatAttribute(attribindex: Int, size: Int, type: Int, normalized: Boolean, relativeoffset: Int) =
        GlobalScope.launch(device.context) {
            glVertexArrayAttribFormat(id, attribindex, size, type, normalized, relativeoffset)
        }

    fun formatIntAttribute(attribindex: Int, size: Int, type: Int, relativeoffset: Int) =
        GlobalScope.launch(device.context) {
            glVertexArrayAttribIFormat(id, attribindex, size, type, relativeoffset)
        }

    fun formatLongAttribute(attribindex: Int, size: Int, type: Int, relativeoffset: Int) =
        GlobalScope.launch(device.context) {
            glVertexArrayAttribLFormat(id, attribindex, size, type, relativeoffset)
        }

}