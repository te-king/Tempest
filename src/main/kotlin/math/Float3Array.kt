package math

inline class Float3Array(val array: FloatArray) : Iterable<Float3> {

    constructor(size: Int) : this(FloatArray(size * 3))


    operator fun get(index: Int): Float3 {
        return Float3(
            array[index * 3],
            array[index * 3 + 1],
            array[index * 3 + 2]
        )
    }

    operator fun set(index: Int, value: Float3) {
        array[index * 3] = value.x
        array[index * 3 + 1] = value.y
        array[index * 3 + 2] = value.z
    }


    val size get() = array.size / 3


    override operator fun iterator() = iterator {

        for (i in 0 until size)
            yield(this@Float3Array[i])

    }

}