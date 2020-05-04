package engine.physics

import engine.world.components.*


abstract class Collider {

    abstract fun enumerateCollisions(thisBody: PhysicsBody, otherBody: PhysicsBody): Sequence<Collision>

}