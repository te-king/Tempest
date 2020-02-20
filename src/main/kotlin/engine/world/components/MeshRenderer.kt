package engine.world.components

import engine.graphics.*
import engine.world.*
import opengl.*

class MeshRenderer(node: Node) : Component(node), Renderable {

    private val transform = node add Transform::class

    val pairs = mutableListOf<Pair<Mesh, Material?>>()


    override fun draw(buffer: CommandBuffer) {

        // Bind transform
        buffer.bindUniformBuffer(2, transform.buffer)

        for ((mesh, material) in pairs) {

            // Bind material information
            if (material != null) {
                buffer.bindUniformBuffer(3, material.buffer)
                buffer.bindPipeline(material.pipeline)
                buffer.bindVertexArray(material.layout)
            }

            for ((index, vertexBuffer) in mesh.vertexBuffers) {
                buffer.bindVertexBuffer(index, vertexBuffer)
            }

            mesh.indexBuffers.forEach { indexBuffer ->
                buffer.bindElementBuffer(indexBuffer)
                buffer.drawIndexedPrimitives(indexBuffer.primitiveType, indexBuffer.indexCount, indexBuffer.indexType)
            }

            for ((index, _) in mesh.vertexBuffers) {
                buffer.bindVertexBuffer(index, null)
            }


        }

    }

}