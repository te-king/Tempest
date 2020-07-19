package engine.graphics

import opengl.Framebuffer

interface Effect {

    fun process(source: Framebuffer, target: Framebuffer)

}