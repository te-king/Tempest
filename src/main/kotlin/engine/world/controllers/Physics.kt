package engine.world.controllers

import engine.physics.Collision
import engine.physics.Sphere
import engine.physics.Surface
import engine.world.Scene
import engine.world.components.PhysicsBody
import engine.world.Updatable
import extensions.findAll
import extensions.pairedPermutations
import math.*

class Physics(scene: Scene) : Controller(scene), Updatable {

    var gravity = Float3(0f, -9.81f, 0f)

    var dampening = 0f


    override fun update(delta: Float) {

        val physicsBodies = scene.findAll(PhysicsBody::class).toList()

        for (physicsBody in physicsBodies) {
            physicsBody.addForce(gravity * delta)
            physicsBody.translationDelta = lerp(physicsBody.translationDelta, Float3.zero, dampening * delta)
            physicsBody.rotationDelta = slerp(physicsBody.rotationDelta, Quaternion.identity, dampening * delta)
        }

        for (pair in physicsBodies.asSequence().pairedPermutations())
            enumerateCollisions(pair.first, pair.second).forEach(this::resolveCollision)

    }


    @JvmName("enumerateAnyAny")
    private fun enumerateCollisions(first: PhysicsBody, second: PhysicsBody) = sequence {
        when (first.collider) {
            is Sphere -> when (second.collider) {
                is Sphere -> {
                }
                is Surface -> {
                    yieldAll(enumerateCollisionsSphereSurface(first, second))
                }
            }
            is Surface -> when (second.collider) {
                is Sphere -> {
                    yieldAll(enumerateCollisionsSphereSurface(second, first))
                }
                is Surface -> {
                }
            }
        }
    }

    @JvmName("enumerateSphereSphere")
    private fun enumerateCollisionsSphereSphere(first: PhysicsBody, second: PhysicsBody) = sequence {
        yield(0)
    }

    @JvmName("enumerateSphereSurface")
    private fun enumerateCollisionsSphereSurface(first: PhysicsBody, second: PhysicsBody) = sequence {
        if (first.transform.translation.y <= 0)
            yield(Collision(first, second, Float3(0f, first.transform.translation.y, 0f)))
    }


    private fun resolveCollision(collision: Collision) {

        val combinedVelocity = collision.first.translationDelta - collision.second.translationDelta
        val collisionDirection = collision.intersection.normalized
        val collisionVelocity = dot(combinedVelocity, collisionDirection)



//        val firstDisplacement = collision.
//
//        collision.first.translationDelta = Float3.zero
//        collision.second.translationDelta = Float3.zero

    }

}