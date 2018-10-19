package math


data class Int4(val x: Int, val y: Int, val z: Int, val w: Int) {

    companion object {
        val SIZE_BITS = Int.SIZE_BITS * 4
        val SIZE_BYTES = Int.SIZE_BYTES * 4
    }


    constructor(): this(0, 0, 0, 0)


    fun toIntArray() = intArrayOf(x, y, z, w)


    operator fun plus(other: Int4) = Int4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun minus(other: Int4) = Int4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun unaryMinus() = Int4(-x, -y, -z, -w)

}