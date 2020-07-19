package engine.physics

import math.Float3
import kotlin.math.min


class Union(val first: Sdf, val second: Sdf) : Sdf {

    override fun march(eye: Float3) = min(first.march(eye), second.march(eye))

}

infix fun Sdf.or(other: Sdf) = Union(this, other)