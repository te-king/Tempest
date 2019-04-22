package math

import extensions.SIZE_BITS
import extensions.SIZE_BYTES


inline class Float4x4 (val data: FloatArray) {

    companion object {
        val SIZE_BITS = Float.SIZE_BITS * 16
        val SIZE_BYTES = Float.SIZE_BYTES * 16

        const val WIDTH = 4
        const val HEIGHT = 4

        val identity = Float4x4(floatArrayOf(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            0f, 0f, 0f, 1f
        ))
    }


    constructor(c0: Float4, c1: Float4, c2: Float4, c3: Float4): this(c0.toFloatArray() + c1.toFloatArray() + c2.toFloatArray() + c3.toFloatArray())


    fun column(index: Int) = when (index) {
        0 -> Float4(data[0], data[1], data[2], data[3])
        1 -> Float4(data[4], data[5], data[6], data[7])
        2 -> Float4(data[8], data[9], data[10], data[11])
        3 -> Float4(data[12], data[13], data[14], data[15])
        else -> throw IndexOutOfBoundsException("$index")
    }


    operator fun get(i: Int, j: Int) = data[i * WIDTH + j]

    operator fun times (other: Float4x4) = Float4x4(floatArrayOf(
        other[0, 0] * this[0, 0] + other[0, 1] * this[1, 0] + other[0, 2] * this[2, 0] + other[0, 3] * this[3, 0],
        other[0, 0] * this[0, 1] + other[0, 1] * this[1, 1] + other[0, 2] * this[2, 1] + other[0, 3] * this[3, 1],
        other[0, 0] * this[0, 2] + other[0, 1] * this[1, 2] + other[0, 2] * this[2, 2] + other[0, 3] * this[3, 2],
        other[0, 0] * this[0, 3] + other[0, 1] * this[1, 3] + other[0, 2] * this[2, 3] + other[0, 3] * this[3, 3],
        other[1, 0] * this[0, 0] + other[1, 1] * this[1, 0] + other[1, 2] * this[2, 0] + other[1, 3] * this[3, 0],
        other[1, 0] * this[0, 1] + other[1, 1] * this[1, 1] + other[1, 2] * this[2, 1] + other[1, 3] * this[3, 1],
        other[1, 0] * this[0, 2] + other[1, 1] * this[1, 2] + other[1, 2] * this[2, 2] + other[1, 3] * this[3, 2],
        other[1, 0] * this[0, 3] + other[1, 1] * this[1, 3] + other[1, 2] * this[2, 3] + other[1, 3] * this[3, 3],
        other[2, 0] * this[0, 0] + other[2, 1] * this[1, 0] + other[2, 2] * this[2, 0] + other[2, 3] * this[3, 0],
        other[2, 0] * this[0, 1] + other[2, 1] * this[1, 1] + other[2, 2] * this[2, 1] + other[2, 3] * this[3, 1],
        other[2, 0] * this[0, 2] + other[2, 1] * this[1, 2] + other[2, 2] * this[2, 2] + other[2, 3] * this[3, 2],
        other[2, 0] * this[0, 3] + other[2, 1] * this[1, 3] + other[2, 2] * this[2, 3] + other[2, 3] * this[3, 3],
        other[3, 0] * this[0, 0] + other[3, 1] * this[1, 0] + other[3, 2] * this[2, 0] + other[3, 3] * this[3, 0],
        other[3, 0] * this[0, 1] + other[3, 1] * this[1, 1] + other[3, 2] * this[2, 1] + other[3, 3] * this[3, 1],
        other[3, 0] * this[0, 2] + other[3, 1] * this[1, 2] + other[3, 2] * this[2, 2] + other[3, 3] * this[3, 2],
        other[3, 0] * this[0, 3] + other[3, 1] * this[1, 3] + other[3, 2] * this[2, 3] + other[3, 3] * this[3, 3]
    ))

    operator fun times (other: Float4) = Float4(
        this[0, 0] * other.x + this[0, 1] * other.y + this[0, 2] * other.z + this[0, 3] * other.w,
        this[1, 0] * other.x + this[1, 1] * other.y + this[1, 2] * other.z + this[1, 3] * other.w,
        this[2, 0] * other.x + this[2, 1] * other.y + this[2, 2] * other.z + this[2, 3] * other.w,
        this[3, 0] * other.x + this[3, 1] * other.y + this[3, 2] * other.z + this[3, 3] * other.w
    )

}