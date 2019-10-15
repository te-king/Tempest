package engine.graphics

import wrappers.opengl.Framebuffer

interface PostProcess {

    fun process(source: Framebuffer, target: Framebuffer)

}