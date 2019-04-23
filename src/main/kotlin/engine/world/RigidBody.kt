package engine.world

import math.Float3
import math.Quaternion
import math.slerp

class RigidBody(node: Node) : Component(node), Updatable {

    val transform = node add Transform::class


    var translationDelta = Float3.zero

    var rotationDelta = Quaternion.identity


    override fun update(delta: Float) {
        transform.translate(translationDelta * delta)
        transform.rotation = slerp(transform.rotation, transform.rotation * rotationDelta, delta)
    }

}