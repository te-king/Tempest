package extensions

import math.*
import kotlin.math.*


// Sizeof Float
inline val Float.Companion.SIZE_BITS get() = Int.SIZE_BITS
inline val Float.Companion.SIZE_BYTES get() = Int.SIZE_BYTES

inline val Float2.Companion.SIZE_BITS get() = Int.SIZE_BITS * 2
inline val Float2.Companion.SIZE_BYTES get() = Int.SIZE_BITS * 2

inline val Float3.Companion.SIZE_BITS get() = Int.SIZE_BITS * 3
inline val Float3.Companion.SIZE_BYTES get() = Int.SIZE_BITS * 3

inline val Float4.Companion.SIZE_BITS get() = Int.SIZE_BITS * 4
inline val Float4.Companion.SIZE_BYTES get() = Int.SIZE_BITS * 4

// Sizeof Int
//inline val Int.Companion.SIZE_BITS get() = Int.SIZE_BITS
//inline val Int.Companion.SIZE_BYTES get() = Int.SIZE_BYTES

inline val Int2.Companion.SIZE_BITS get() = Int.SIZE_BITS * 2
inline val Int2.Companion.SIZE_BYTES get() = Int.SIZE_BITS * 2

inline val Int3.Companion.SIZE_BITS get() = Int.SIZE_BITS * 3
inline val Int3.Companion.SIZE_BYTES get() = Int.SIZE_BITS * 3

inline val Int4.Companion.SIZE_BITS get() = Int.SIZE_BITS * 4
inline val Int4.Companion.SIZE_BYTES get() = Int.SIZE_BITS * 4


//inline val Double.Companion.SIZE_BITS get() = Long.SIZE_BITS
//inline val Double.Companion.SIZE_BYTES get() = Long.SIZE_BYTES


// Vector Functions
fun lerp(v0: Float2, v1: Float2, amount: Float) = v0 + (v1 - v0) * amount
fun lerp(v0: Float3, v1: Float3, amount: Float) = v0 + (v1 - v0) * amount
fun lerp(v0: Float4, v1: Float4, amount: Float) = v0 + (v1 - v0) * amount

fun distance(v0: Float2, v1: Float2) = (v1 - v0).length
fun distance(v0: Float3, v1: Float3) = (v1 - v0).length
fun distance(v0: Float4, v1: Float4) = (v1 - v0).length

fun dot(v0: Float2, v1: Float2) = v0.x * v1.x + v0.y * v1.y
fun dot(v0: Float3, v1: Float3) = v0.x * v1.x + v0.y * v1.y + v0.z * v1.z
fun dot(v0: Float4, v1: Float4) = v0.x * v1.x + v0.y * v1.y + v0.z * v1.z + v0.w * v1.w

fun cross(v0: Float3, v1: Float3) = Float3(v0.y * v1.z - v0.z * v1.y, v0.z * v1.x - v0.x * v1.z, v0.x * v1.y - v0.y * v1.x)


fun slerp(first: Quaternion, second: Quaternion, amount: Float): Quaternion {

    val d = dot(first.vector, second.vector).absoluteValue

    val scale0: Float
    val scale1: Float

    if (d < 0.9f) {
        val angle = acos(d)
        val invSinTheta = 1f / sin(angle)
        scale0 = sin((1f - amount) * angle) * if (d < 0f) -invSinTheta else invSinTheta
        scale1 = sin(amount * angle) * invSinTheta
    } else {
        scale0 = 1f - amount
        scale1 = amount
    }

    return Quaternion(
        scale0 * first.x + scale1 * second.x,
        scale0 * first.y + scale1 * second.y,
        scale0 * first.z + scale1 * second.z,
        scale0 * first.w + scale1 * second.w
    )
}