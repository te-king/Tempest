package engine.physics

import math.Float3


class Sphere(val radius: Float) : Collider, Sdf {

    override fun march(eye: Float3) = eye.length - radius

}


