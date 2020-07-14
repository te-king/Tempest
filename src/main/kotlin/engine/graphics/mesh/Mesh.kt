package engine.graphics.mesh

import math.Float4Array
import math.Int3Array
import opengl.Device
import opengl.MeshBuffer


abstract class Mesh : Iterable<SubMesh> {

    abstract val vertexArrays: Map<Int, Float4Array>

    abstract val elementArrays: List<Int3Array>


    override fun iterator() = elementArrays
        .map {
            SubMesh(vertexArrays, it)
        }
        .iterator()

}


