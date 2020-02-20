package engine.world

import opengl.CommandBuffer

interface Renderable {
    fun draw(buffer: CommandBuffer)
}