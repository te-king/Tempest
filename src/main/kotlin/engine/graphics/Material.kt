package engine.graphics

import opengl.*

interface Material {
    val buffer: Buffer<UniformBuffer, *>
    val pipeline: Pipeline
    val layout: VertexArray
}