package engine.world.components

import engine.world.Node
import engine.world.controllers.Renderer
import opengl.CommandBuffer

class GuiRenderer(node: Node) : Component(node), Renderer.Renderable {

    override fun draw(buffer: CommandBuffer) {
        TODO("Not yet implemented")
    }

}