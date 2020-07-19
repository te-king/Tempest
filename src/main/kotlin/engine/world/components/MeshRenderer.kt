package engine.world.components

import engine.graphics.*
import engine.world.*
import engine.world.controllers.Renderer
import opengl.*


class MeshRenderer(node: Node) : Component(node), Renderer.Renderable {

    private val transform = component<Transform>()

    val pairs = mutableListOf<Pair<MeshBuffer, Material?>>()


    override fun draw(buffer: CommandBuffer) {

        // Bind transform
        transform.worldMatrix
        buffer.bindUniformBuffer(2, transform.buffer)

        for (group in pairs.groupBy { it.second })
            group.key?.draw(buffer, group.value.asSequence().map { it.first })

    }

}