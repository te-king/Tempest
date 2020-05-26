package engine.graphics.mesh

import math.Float3Array


class SubMesh(val vertexArrays: Map<Int, Float3Array>, val elements: IntArray) : Iterable<Face> {

    override fun iterator() =
        elements.map { index -> Face(vertexArrays.map { it.key to it.value[index] }.toMap()) }.iterator()

}