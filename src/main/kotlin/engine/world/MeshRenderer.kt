package engine.world

import wrappers.opengl.CommandBuffer
import engine.graphics.Material
import engine.graphics.Mesh

class MeshRenderer (node: Node) : Component(node) {

    private val transform = node add Transform::class


    var mesh: Mesh? = null

    var material: Material? = null


    fun draw(buffer: CommandBuffer) {
        mesh?.let { mesh ->
            material?.let { material ->

                buffer.bindPipeline(material.pipeline)
                buffer.bindVertexArray(material.layout)

                buffer.bindBuffer(2, transform.buffer)
                buffer.bindBuffer(3, material.buffer)

                mesh.vertexBuffers.forEach(buffer::bindVertexBuffer)

                mesh.indexBuffers.forEach { indexBuffer ->
                    buffer.bindElementBuffer(indexBuffer)
                    buffer.drawIndexedPrimitives(indexBuffer.primitiveType, indexBuffer.indexCount, indexBuffer.indexType)
                }

                mesh.vertexBuffers.mapValues { null }.forEach(buffer::bindVertexBuffer)

            }
        }
    }

}