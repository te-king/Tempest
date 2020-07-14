package math


inline class Int3Array(val array: IntArray) : Iterable<Int3> {

    constructor(size: Int) : this(IntArray(size * 3))


    operator fun get(index: Int): Int3 {
        return Int3(
            array[index * 3],
            array[index * 3 + 1],
            array[index * 3 + 2]
        )
    }

    operator fun set(index: Int, value: Int3) {
        array[index * 3] = value.x
        array[index * 3 + 1] = value.y
        array[index * 3 + 2] = value.z
    }


    val size get() = array.size / 3


    override operator fun iterator() = iterator {

        for (i in 0 until size)
            yield(this@Int3Array[i])

    }

}