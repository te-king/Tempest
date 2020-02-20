package engine.graphics

import opengl.Buffer
import opengl.Pipeline
import opengl.UniformBuffer
import opengl.VertexArray

interface Material {
    val buffer: Buffer<UniformBuffer, *>
    val pipeline: Pipeline
    val layout: VertexArray
}