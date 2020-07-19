package engine.physics

import math.Float3


// https://www.iquilezles.org/www/articles/distfunctions/distfunctions.htm
interface Sdf {

    fun march(eye: Float3): Float

}