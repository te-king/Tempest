package engine.world

import wrappers.opengl.CommandBuffer

interface Renderable {
    fun draw(buffer: CommandBuffer)
}