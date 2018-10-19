package engine.graphics

import wrappers.opengl.Buffer
import wrappers.opengl.Pipeline

interface Material {
    val buffer: Buffer
    val pipeline: Pipeline
}