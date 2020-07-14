package engine.graphics.mesh

import math.Float4


data class Face(val v0: Map<Int, Float4>, val v1: Map<Int, Float4>, val v2: Map<Int, Float4>) {

    override fun toString() = "${v0[0]} - ${v1[0]} - ${v2[0]}"

}