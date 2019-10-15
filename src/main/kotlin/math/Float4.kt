package math

import extensions.SIZE_BITS
import extensions.SIZE_BYTES
import org.lwjgl.assimp.AIColor4D
import kotlin.math.sqrt


data class Float4(val x: Float, val y: Float, val z: Float, val w: Float) {

    companion object {
        val SIZE_BITS = Float.SIZE_BITS * 4
        val SIZE_BYTES = Float.SIZE_BYTES * 4

        val zero = Float4(0f, 0f, 0f, 0f)
        val one = Float4(1f, 1f, 1f, 1f)
    }


    constructor(v0: Float2, v1: Float2) : this(v0.x, v0.y, v1.x, v1.y)
    constructor(v: Float3, w: Float) : this(v.x, v.y, v.z, w)
    constructor() : this(0f, 0f, 0f, 0f)
    internal constructor(other: AIColor4D) : this(other.r(), other.g(), other.b(), other.a())


    val lengthSquared get() = x * x + y * y + z * z + w * w
    val length get() = sqrt(lengthSquared)
    val normalized get() = this / length
    val xyz get() = Float3(x, y, z)
    val yzw get() = Float3(y, z, w)


    fun toFloatArray() = floatArrayOf(x, y, z, w)


    operator fun plus(other: Float4) = Float4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Float4) = Float4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun times(scalar: Float) = Float4(x * scalar, y * scalar, z * scalar, w * scalar)
    operator fun div(scalar: Float) = Float4(x / scalar, y / scalar, z / scalar, w / scalar)
    operator fun unaryMinus() = Float4(-x, -y, -z, -w)

}

fun lerp(first: Float4, second: Float4, amount: Float) = first + (second - first) * amount
fun dot(first: Float4, second: Float4) = first.x * second.x + first.y * second.y + first.z * second.z + first.w * second.w