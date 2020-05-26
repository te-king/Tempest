package math

inline class Float4Array(val array: FloatArray) : Iterable<Float4> {

    constructor(size: Int) : this(FloatArray(size * 4))


    operator fun get(index: Int): Float4 {
        return Float4(
            array[index * 4],
            array[index * 4 + 1],
            array[index * 4 + 2],
            array[index * 4 + 3]
        )
    }

    operator fun set(index: Int, value: Float4) {
        array[index * 4] = value.x
        array[index * 4 + 1] = value.y
        array[index * 4 + 2] = value.z
        array[index * 4 + 3] = value.w
    }


    val size get() = array.size / 4


    override operator fun iterator() = iterator {

        for (i in 0 until size)
            yield(this@Float4Array[i])

    }

}