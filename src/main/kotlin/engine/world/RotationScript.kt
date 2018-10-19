package engine.world

import math.Float3

class RotationScript(node: Node) : Component(node), Updatable {

    private val transform = node add Transform::class


    private val speed = 1f

    override fun update(delta: Float) {
        transform.rotate(Float3.up, speed * delta)
    }

}