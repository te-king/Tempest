package engine.physics

import engine.world.components.*
import math.*


class Line(val direction: Float3) : Collider() {

    override fun enumerateCollisions(thisBody: PhysicsBody, otherBody: PhysicsBody) =
        when (val other = otherBody.collider) {
            is Sphere -> other.enumerateCollisions(otherBody, thisBody)
            else -> emptySequence()
        }

}