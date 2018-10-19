package engine.graphics

import common.ReadWriteIndexer
import wrappers.opengl.*

class Mesh (val device: Device) {

    val vao = device.vertexArray()

    val vertexBuffers = object: ReadWriteIndexer<VertexBuffer?> {

        private val meshBuffers = mutableMapOf<Int, VertexBuffer>()

        override fun get(index: Int) = meshBuffers[index]

        override fun set(index: Int, value: VertexBuffer?) {
            if (value != null) {
                meshBuffers[index] = value
                vao.vertexArrayVertexBuffer(index, value.buffer, value.offset, value.stride)
            } else {
                meshBuffers.remove(index)
            }
        }

    }

    var indexBuffer: Buffer? = null
        set(value) {
            vao.vertexArrayElementBuffer(value)
            field = value
        }

    var indexCount = 0
    var indexType = IndexType.UNSIGNED_INT
    var primitiveType = PrimitiveType.TRIANGLES


    operator fun get(index: Int) = Attribute(index)


    class VertexBuffer (val buffer: Buffer, val offset: Long, val stride: Int)

    inner class Attribute(val index: Int) {

        var enabled = false
            set(value) {
                if (value) vao.enableVertexArrayAttrib(index) else vao.disableVertexArrayAttrib(index)
                field = value
            }

        var bufferIndex = -1
            set(value) {
                vao.vertexArrayAttribBinding(index, value)
                field = value
            }

        fun format(size: Int, type: Int, normalized: Boolean = false, relativeoffset: Int = 0) = vao.vertexArrayAttribFormat(index, size, type, normalized, relativeoffset)

    }

}