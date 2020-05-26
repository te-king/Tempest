package math

inline class Float4x4Array(val array: FloatArray) : Iterable<Float4x4> {

    constructor(size: Int) : this(FloatArray(size * (4 * 4)))


    operator fun get(index: Int): Float4x4 {
        return Float4x4(
            array[index * (4 * 4)],
            array[index * (4 * 4) + 1],
            array[index * (4 * 4) + 2],
            array[index * (4 * 4) + 3],
            array[index * (4 * 4) + 4],
            array[index * (4 * 4) + 5],
            array[index * (4 * 4) + 6],
            array[index * (4 * 4) + 7],
            array[index * (4 * 4) + 8],
            array[index * (4 * 4) + 9],
            array[index * (4 * 4) + 10],
            array[index * (4 * 4) + 11],
            array[index * (4 * 4) + 12],
            array[index * (4 * 4) + 13],
            array[index * (4 * 4) + 14],
            array[index * (4 * 4) + 15]
        )
    }

    operator fun set(index: Int, value: Float4x4) {
        array[index * (4 * 4)] = value.m00
        array[index * (4 * 4) + 1] = value.m01
        array[index * (4 * 4) + 2] = value.m02
        array[index * (4 * 4) + 3] = value.m03
        array[index * (4 * 4) + 4] = value.m10
        array[index * (4 * 4) + 5] = value.m11
        array[index * (4 * 4) + 6] = value.m12
        array[index * (4 * 4) + 7] = value.m13
        array[index * (4 * 4) + 8] = value.m20
        array[index * (4 * 4) + 9] = value.m21
        array[index * (4 * 4) + 10] = value.m22
        array[index * (4 * 4) + 11] = value.m23
        array[index * (4 * 4) + 12] = value.m30
        array[index * (4 * 4) + 13] = value.m31
        array[index * (4 * 4) + 14] = value.m32
        array[index * (4 * 4) + 15] = value.m33
    }


    val size get() = array.size / (4 * 4)


    override operator fun iterator() = iterator {

        for (i in 0 until size)
            yield(this@Float4x4Array[i])

    }

}