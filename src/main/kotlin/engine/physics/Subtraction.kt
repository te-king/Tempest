package engine.physics

import math.Float3
import kotlin.math.max


class Subtraction(val first: Sdf, val second: Sdf) : Sdf {

    override fun march(eye: Float3) = max(-first.march(eye), second.march(eye))

}

operator fun Sdf.minus(other: Sdf) = Subtraction(this, other)