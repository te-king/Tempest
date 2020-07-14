package engine.graphics.mesh

import math.Float4Array
import math.Int3Array

class TriangleMesh : Mesh() {

    override val vertexArrays = mapOf(
        0 to Float4Array( // position
            floatArrayOf(
                0f, 0f, 0f, 1f,
                0f, 1f, 0f, 1f,
                1f, 0f, 0f, 1f
            )
        ),
        1 to Float4Array( // normal
            floatArrayOf(
                0f, 0f, -1f, 0f,
                0f, 0f, -1f, 0f,
                0f, 0f, -1f, 0f
            )
        ),
        2 to Float4Array( // uv
            floatArrayOf(
                0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f,
                1f, 0f, 0f, 0f
            )
        ),
        3 to Float4Array( // tangent
            floatArrayOf(
                0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f
            )
        )
    )

    override val elementArrays = listOf(
        Int3Array(intArrayOf(0, 1, 2))
    )

}