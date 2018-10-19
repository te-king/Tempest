package engine.world

import wrappers.opengl.RenderCommandBuffer

interface Drawable {
    fun draw(buffer: RenderCommandBuffer)
}