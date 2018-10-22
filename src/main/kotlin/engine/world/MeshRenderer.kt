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
                buffer.bindUniformBuffer(2, transform.buffer)
                buffer.bindUniformBuffer(3, material.buffer)
                buffer.bindPipeline(material.pipeline)
                buffer.bindVertexArray(mesh.vao)
                buffer.drawIndexedPrimitives(mesh.primitiveType, mesh.indexCount, mesh.indexType)
            }
        }
    }

}