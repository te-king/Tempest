package engine.graphics.mesh

import math.Float3Array

abstract class Mesh : Iterable<SubMesh> {

    abstract val vertexArrays: Map<Int, Float3Array>
    abstract val elementArrays: List<IntArray>

    val faces get() = flatten()

    val size get() = elementArrays.sumBy { it.size }


    operator fun get(index: Int) =
        SubMesh(vertexArrays, elementArrays[index])


    override fun iterator() =
        elementArrays.map { SubMesh(vertexArrays, it) }.iterator()

}


