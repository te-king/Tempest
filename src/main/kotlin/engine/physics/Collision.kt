package engine.physics

import engine.world.components.PhysicsBody
import math.Float3

/**
 * intersection:
 * the distance the first element has fowled with the second item
 */
data class Collision(val first: PhysicsBody, val second: PhysicsBody, val intersection: Float3)