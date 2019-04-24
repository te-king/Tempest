package engine.world

import extensions.findAll
import math.Float3
import math.Quaternion
import math.lerp
import math.slerp

class WorldController(node: Node) : Component(node), Updatable {

    var gravity = Float3(0f, -9.81f, 0f)

    var dampening = 1f


    override fun update(delta: Float) {

        for (it in node findAll PhysicsBody::class) {
            it.addForce(gravity * delta)
            it.translationDelta = lerp(it.translationDelta, Float3.zero, dampening * delta)
            it.rotationDelta = slerp(it.rotationDelta, Quaternion.identity, dampening * delta)
        }

    }

}