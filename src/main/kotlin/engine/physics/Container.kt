package engine.physics

import engine.world.components.*


object Container : Collider() {

    override fun enumerateCollisions(thisBody: PhysicsBody, otherBody: PhysicsBody) =
        when (val other = otherBody.collider) {
            is Container -> emptySequence()
            else -> other.enumerateCollisions(otherBody, thisBody)
        }

}