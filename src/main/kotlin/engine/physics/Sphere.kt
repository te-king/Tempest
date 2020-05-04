package engine.physics

import engine.world.components.*
import math.*


class Sphere(val radius: Float) : Collider() {

    override fun enumerateCollisions(thisBody: PhysicsBody, otherBody: PhysicsBody) =
        when (val other = otherBody.collider) {
            is Container -> sequence {

                val sep = thisBody.transform.translation - otherBody.transform.translation

                if (sep.x < -.5)
                    yield(
                        Collision(thisBody, otherBody, Float3.left, sep.x + .5f)
                    )

                if (sep.x > .5)
                    yield(
                        Collision(thisBody, otherBody, Float3.right, -sep.x + .5f)
                    )

                if (sep.y < 0)
                    yield(
                        Collision(thisBody, otherBody, Float3.down, sep.y)
                    )

                if (sep.z < -.5)
                    yield(
                        Collision(thisBody, otherBody, Float3.forwards, sep.z + .5f)
                    )

                if (sep.z > .5)
                    yield(
                        Collision(thisBody, otherBody, Float3.backwards, -sep.z + .5f)
                    )

            }
            is Sphere -> sequence {

                val combinedRadius = radius + other.radius
                val translationDelta = otherBody.transform.translation - thisBody.transform.translation

                val seperationSquared = translationDelta.lengthSquared

                if (seperationSquared < (combinedRadius * combinedRadius)) {

                    val seperation = kotlin.math.sqrt(seperationSquared)

                    yield(
                        Collision(thisBody, otherBody, translationDelta / seperation, seperation - combinedRadius)
                    )

                }

            }
            is Line -> emptySequence()
            is Point -> emptySequence()
            else -> emptySequence()
        }

}