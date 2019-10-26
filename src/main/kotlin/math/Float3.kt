package math

import extensions.SIZE_BITS
import extensions.SIZE_BYTES
import org.lwjgl.assimp.AIVector3D
import kotlin.math.sqrt


data class Float3(val x: Float, val y: Float, val z: Float) {

    companion object {
        val SIZE_BITS = Float.SIZE_BITS * 3
        val SIZE_BYTES = Float.SIZE_BYTES * 3

        val zero = Float3(0f, 0f, 0f)
        val one = Float3(1f, 1f, 1f)

        val up = Float3(0f, 1f, 0f)
        val down = -up
        val left = Float3(-1f, 0f, 0f)
        val right = -left
        val forwards = Float3(0f, 0f, -1f)
        val backwards = -forwards
    }


    constructor(): this(0f, 0f, 0f)
    internal constructor(other: AIVector3D): this(other.x(), other.y(), other.z())


    val lengthSquared get() = x * x + y * y + z * z
    val length get() = sqrt(lengthSquared)
    val normalized get() = this / length


    fun toFloatArray() = floatArrayOf(x, y, z)


    operator fun plus (other: Float3) = Float3(x + other.x, y + other.y, z + other.z)
    operator fun minus (other: Float3) = Float3(x - other.x, y - other.y, z - other.z)
    operator fun times (scalar: Float) = Float3(x * scalar, y * scalar, z * scalar)
    operator fun div (scalar: Float) = Float3(x / scalar, y / scalar, z / scalar)
    operator fun unaryMinus () = Float3(-x, -y, -z)

}