package math

import kotlin.math.sqrt


data class Float2(val x: Float, val y: Float) {

    companion object {
        val zero = Float2(0f, 0f)
        val one = Float2(1f, 1f)
    }


    constructor() : this(0f, 0f)


    val lengthSquared get() = x * x + y * y
    val length get() = sqrt(lengthSquared)
    val normalized get() = this / length


    fun toFloatArray() = floatArrayOf(x, y)


    operator fun plus(other: Float2) = Float2(x + other.x, y + other.y)
    operator fun minus(other: Float2) = Float2(x - other.x, y - other.y)
    operator fun times(scalar: Float) = Float2(x * scalar, y * scalar)
    operator fun div(scalar: Float) = Float2(x / scalar, y / scalar)
    operator fun unaryMinus() = Float2(-x, -y)

}