package engine.graphics

import opengl.Framebuffer

interface PostProcess {

    fun process(source: Framebuffer, target: Framebuffer)

}