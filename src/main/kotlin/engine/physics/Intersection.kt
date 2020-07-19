package engine.physics

import math.Float3
import kotlin.math.max


class Intersection(val first: Sdf, val second: Sdf) : Sdf {

    override fun march(eye: Float3) = max(first.march(eye), second.march(eye))

}

infix fun Sdf.and(other: Sdf) = Intersection(this, other)