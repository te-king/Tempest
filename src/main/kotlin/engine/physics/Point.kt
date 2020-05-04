package engine.physics

import engine.world.components.*


class Point : Collider() {

    override fun enumerateCollisions(thisBody: PhysicsBody, otherBody: PhysicsBody) =
        when (val other = otherBody.collider) {
            is Sphere -> other.enumerateCollisions(otherBody, thisBody)
            else -> emptySequence()
        }

}


