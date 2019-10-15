package engine.graphics

import wrappers.opengl.Buffer
import wrappers.opengl.Device
import wrappers.opengl.IndexType
import wrappers.opengl.PrimitiveType

class Mesh(val device: Device) {

    val vertexBuffers = mutableMapOf<Int, VertexBuffer>()
    val indexBuffers = mutableListOf<IndexBuffer>()

    class VertexBuffer(val buffer: Buffer, val offset: Long, val stride: Int)
    class IndexBuffer(val buffer: Buffer, val indexCount: Int, val indexType: IndexType, val primitiveType: PrimitiveType)

}

