package math


inline class Float2Array(val array: FloatArray) : Iterable<Float2> {

    constructor(size: Int) : this(FloatArray(size * 2))


    operator fun get(index: Int): Float2 {
        return Float2(
            array[index * 2],
            array[index * 2 + 1]
        )
    }

    operator fun set(index: Int, value: Float2) {
        array[index * 2] = value.x
        array[index * 2 + 1] = value.y
    }


    val size get() = array.size / 2


    override operator fun iterator() = iterator {

        for (i in 0 until size)
            yield(this@Float2Array[i])

    }

}
