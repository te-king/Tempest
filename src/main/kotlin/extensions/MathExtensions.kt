package extensions

import math.*
import org.lwjgl.assimp.*
import kotlin.math.*
import kotlin.reflect.KClass
import kotlin.system.exitProcess


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


// sizeof integers
@JvmName("byteSizeOf")
inline fun sizeOf(type: KClass<Byte>) = Byte.SIZE_BYTES.toLong()

@JvmName("shortSizeOf")
inline fun sizeOf(type: KClass<Short>) = Short.SIZE_BYTES.toLong()

@JvmName("intSizeOf")
inline fun sizeOf(type: KClass<Int>) = Int.SIZE_BYTES.toLong()

@JvmName("longSizeOf")
inline fun sizeOf(type: KClass<Long>) = Long.SIZE_BYTES.toLong()


@JvmName("int2SizeOf")
inline fun sizeOf(type: KClass<Int2>) = sizeOf(Int::class) * 2

@JvmName("int3SizeOf")
inline fun sizeOf(type: KClass<Int3>) = sizeOf(Int::class) * 3

@JvmName("int4SizeOf")
inline fun sizeOf(type: KClass<Int4>) = sizeOf(Int::class) * 4


// sizeof float
@JvmName("floatSizeOf")
inline fun sizeOf(type: KClass<Float>) = Int.SIZE_BYTES.toLong()

@JvmName("doubleSizeOf")
inline fun sizeOf(type: KClass<Double>) = Int.SIZE_BYTES.toLong()


@JvmName("float2SizeOf")
inline fun sizeOf(type: KClass<Float2>) = sizeOf(Float::class) * 2

@JvmName("float3SizeOf")
inline fun sizeOf(type: KClass<Float3>) = sizeOf(Float::class) * 3

@JvmName("float4SizeOf")
inline fun sizeOf(type: KClass<Float4>) = sizeOf(Float::class) * 4

@JvmName("float4x4SizeOf")
inline fun sizeOf(type: KClass<Float4x4>) = sizeOf(Float::class) * 4 * 4


@JvmName("colorSizeOf")
inline fun sizeOf(type: KClass<Color>) = sizeOf(Float4::class)

@JvmName("quatSizeOf")
inline fun sizeOf(type: KClass<Quaternion>) = sizeOf(Float4::class)

@JvmName("projSizeOf")
inline fun sizeOf(type: KClass<ProjectionMatrix>) = sizeOf(Float4x4::class)

@JvmName("transSizeOf")
inline fun sizeOf(type: KClass<TransformationMatrix>) = sizeOf(Float4x4::class)


fun sizeOf(vararg types: KClass<*>) =
    types.map {
        when (it) {
            Byte::class -> sizeOf(Byte::class)
            Short::class -> sizeOf(Short::class)
            Int::class -> sizeOf(Int::class)
            Long::class -> sizeOf(Long::class)
            Int2::class -> sizeOf(Int2::class)
            Int3::class -> sizeOf(Int3::class)
            Int4::class -> sizeOf(Int4::class)
            Float::class -> sizeOf(Float::class)
            Double::class -> sizeOf(Double::class)
            Float2::class -> sizeOf(Float2::class)
            Float3::class -> sizeOf(Float3::class)
            Float4::class -> sizeOf(Float4::class)
            Float4x4::class -> sizeOf(Float4x4::class)
            Color::class -> sizeOf(Color::class)
            Quaternion::class -> sizeOf(Quaternion::class)
            ProjectionMatrix::class -> sizeOf(ProjectionMatrix::class)
            TransformationMatrix::class -> sizeOf(TransformationMatrix::class)
            else -> 0
        }
    }.sum()


// conversions
fun AIColor3D.toColor() = Color(r(), g(), b(), 1f)
fun AIColor4D.toColor() = Color(r(), g(), b(), a())
fun AIVector2D.toFloat2() = Float2(x(), y())
fun AIVector3D.toFloat3() = Float3(x(), y(), z())
fun AIQuaternion.toQuaternion() = Quaternion(x(), y(), z(), w())