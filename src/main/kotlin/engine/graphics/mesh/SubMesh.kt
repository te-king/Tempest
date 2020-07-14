package engine.graphics.mesh

import math.Float4Array
import math.Int3Array


data class SubMesh(val vertexArrays: Map<Int, Float4Array>, val elementArray: Int3Array) : Iterable<Face> {

    override fun iterator() = elementArray
        .asSequence()
        .map { element ->
            val v0 = vertexArrays.map { it.key to it.value[element.x] }.toMap()
            val v1 = vertexArrays.map { it.key to it.value[element.y] }.toMap()
            val v2 = vertexArrays.map { it.key to it.value[element.z] }.toMap()
            Face(v0, v1, v2)
        }
        .iterator()

}


