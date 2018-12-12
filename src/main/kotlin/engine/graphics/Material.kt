package engine.graphics

import wrappers.opengl.Buffer
import wrappers.opengl.Pipeline
import wrappers.opengl.VertexArray

interface Material {
    val buffer: Buffer
    val pipeline: Pipeline
    val layout: VertexArray
}