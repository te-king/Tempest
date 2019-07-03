package engine.world.components

import engine.world.Node
import engine.world.Updatable
import math.Float3
import math.Quaternion
import math.slerp

class PhysicsBody(node: Node) : Component(node), Updatable {

    val transform = node add Transform::class


    var translationDelta = Float3.zero

    var rotationDelta = Quaternion.identity


    fun addForce(force: Float3) {
        translationDelta += force
    }


    override fun update(delta: Float) {
        transform.translate(translationDelta * delta)
        transform.rotation = slerp(transform.rotation, transform.rotation * rotationDelta, delta)
    }

}