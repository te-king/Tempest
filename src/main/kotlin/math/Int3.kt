package math


data class Int3(val x: Int, val y: Int, val z: Int) {

    companion object {
        val SIZE_BITS = Int.SIZE_BITS * 3
        val SIZE_BYTES = Int.SIZE_BYTES * 3
    }


    constructor(): this(0, 0, 0)


    fun toIntArray() = intArrayOf(x, y, z)


    operator fun plus (other: Int3) = Int3(x + other.x, y + other.y, z + other.z)
    operator fun minus (other: Int3) = Int3(x - other.x, y - other.y, z - other.z)
    operator fun unaryMinus () = Int3(-x, -y, -z)

}