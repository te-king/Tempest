package math

import java.util.*

data class Int2(val x: Int, val y: Int) {

    companion object {
        val SIZE_BITS = Int.SIZE_BITS * 2
        val SIZE_BYTES = Int.SIZE_BYTES * 2
    }


    constructor(): this(0, 0)


    fun toIntArray() = intArrayOf(x, y)


    operator fun plus (other: Int2) = Int2(x + other.x, y + other.y)
    operator fun minus (other: Int2) = Int2(x - other.x, y - other.y)
    operator fun unaryMinus () = Int2(-x, -y)

}