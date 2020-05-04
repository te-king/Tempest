package math

import java.util.*

data class Int2(val x: Int, val y: Int) {

    companion object {
        val zero = Int2(0, 0)
        val one = Int2(1, 1)
    }


    constructor() : this(0, 0)


    fun toIntArray() = intArrayOf(x, y)


    operator fun plus(other: Int2) = Int2(x + other.x, y + other.y)
    operator fun minus(other: Int2) = Int2(x - other.x, y - other.y)
    operator fun unaryMinus() = Int2(-x, -y)

}