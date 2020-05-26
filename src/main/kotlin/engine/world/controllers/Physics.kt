package engine.world.controllers

import engine.physics.Collision
import engine.physics.collide
import engine.world.Scene
import engine.world.Updatable
import engine.world.components.PhysicsBody
import extensions.*
import math.Float3
import math.Quaternion

class Physics(scene: Scene) : Controller(scene), Updatable {


    var gravity = Float3(0f, -9.81f, 0f)


    override fun update(delta: Float) {

        val physicsBodies = scene.findAll(PhysicsBody::class).toList()

        for (physicsBody in physicsBodies) {
            physicsBody.translationDelta += gravity * delta
            physicsBody.translationDelta = lerp(physicsBody.translationDelta, Float3.zero, delta)
            physicsBody.rotationDelta = slerp(physicsBody.rotationDelta, Quaternion.identity, delta)
        }

        for ((first, second) in physicsBodies.asSequence().pairedPermutations())
            collide(first, second).forEach(::resolveCollision)

    }


    private fun resolveCollision(collision: Collision) {

        val relativeVelocity = collision.second.translationDelta - collision.first.translationDelta
        val velocityAlongNormal = dot(relativeVelocity, collision.normal)

        // Handle intersection
        val displacementScalar = collision.seperation / (collision.first.massInverse + collision.second.massInverse)
        val displacement = collision.normal * displacementScalar
        collision.first.transform.translation += displacement * collision.first.massInverse
        collision.second.transform.translation -= displacement * collision.second.massInverse


        // If we're moving away, ignore
        if (velocityAlongNormal < 0) {

            // Handle change in motion
            val impulseScalar = -(1 + collision.restitution) * velocityAlongNormal / (collision.first.massInverse + collision.second.massInverse)
            val impulse = collision.normal * impulseScalar
            collision.first.translationDelta -= impulse * collision.first.massInverse
            collision.second.translationDelta += impulse * collision.second.massInverse

        }

    }

}