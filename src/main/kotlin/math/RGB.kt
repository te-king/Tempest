package math

inline class RGB(val vector: Float3) {

    constructor(red: Float, green: Float, blue: Float) : this(Float3(red, green, blue))

    inline val red get() = vector.x
    inline val green get() = vector.y
    inline val blue get() = vector.z


    override fun toString() = "RGB(r=$red, g=$green, b=$blue)"

}


