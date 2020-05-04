package engine.graphics

import extensions.SIZE_BYTES
import math.Float3
import math.Float3Array
import opengl.*

class Geometry(val device: Device) {

    val vertexBuffers = mutableMapOf<Int, VertexBuffer>()
    val indexBuffers = mutableListOf<IndexBuffer>()

    class VertexBuffer(val buffer: Buffer<ArrayBuffer, *>, val offset: Long, val stride: Int)
    class IndexBuffer(val buffer: Buffer<ElementArrayBuffer, *>, val indexCount: Int, val indexType: IndexType, val primitiveType: PrimitiveType)

}


class Face(val vertices: Map<Int, Float3>)


class SubMesh(val vertexArrays: Map<Int, Float3Array>, val elements: IntArray) : Iterable<Face> {

    override fun iterator() =
        elements.map { index -> Face(vertexArrays.map { it.key to it.value[index] }.toMap()) }.iterator()

}

class Mesh(val vertexArrays: Map<Int, Float3Array>, val elementArrays: List<IntArray>) : Iterable<SubMesh> {


    val faces get() = flatten()

    val size get() = elementArrays.sumBy { it.size }


    fun generateGraphicsObject(device: Device) {

        val vertexBuffers = vertexArrays
            .map {
                Geometry.VertexBuffer(
                    device.buffer(it.value.array, ArrayBuffer, ServerStorage),
                    0,
                    Float3.SIZE_BYTES
                )
            }

        val indexBuffers = elementArrays
            .map {
                Geometry.IndexBuffer(
                    device.buffer(it, ElementArrayBuffer, ServerStorage),
                    it.size,
                    IndexType.UNSIGNED_INT,
                    PrimitiveType.Triangles
                )
            }

    }


    operator fun get(index: Int) =
        SubMesh(vertexArrays, elementArrays[index])


    override fun iterator() =
        elementArrays.map { SubMesh(vertexArrays, it) }.iterator()

}