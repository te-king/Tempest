package opengl

import opengl.ArrayBuffer
import opengl.Buffer


class VertexBuffer(val buffer: Buffer<ArrayBuffer, *>, val offset: Long, val stride: Int)