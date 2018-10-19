package engine.world

import wrappers.opengl.CommandBuffer

interface Drawable {
    fun draw(buffer: CommandBuffer)
}