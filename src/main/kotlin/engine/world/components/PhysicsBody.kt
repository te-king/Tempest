package engine.world.components

import engine.physics.Collider
import engine.physics.Sphere
import engine.world.*
import math.*

class PhysicsBody(node: Node) : Component(node), Updatable {

    val transform = node add Transform::class

    var collider: Collider = Sphere(1f)

    var static = false

    var mass = 1f
        get() = if (static) Float.MAX_VALUE else field

    var translationDelta = Float3.zero
        get() = if (static) Float3.zero else field

    var rotationDelta = Quaternion.identity
        get() = if (static) Quaternion.identity else field


    fun addForce(force: Float3) {
        translationDelta += force
    }


    override fun update(delta: Float) {
        transform.translate(translationDelta * delta)
        transform.rotation = slerp(transform.rotation, transform.rotation * rotationDelta, delta)
    }

}