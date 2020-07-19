package engine.physics

import math.Float3

class Box(val size: Float3) : Sdf {

    override fun march(eye: Float3): Float {
        val q = eye.absoluteValue - size
        TODO("Not yet implemented")
    }

}