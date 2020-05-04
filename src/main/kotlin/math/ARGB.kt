package math

inline class ARGB(val vector: Float4) {

    constructor(alpha: Float, red: Float, green: Float, blue: Float) : this(Float4(alpha, red, green, blue))


    inline val alpha get() = vector.x
    inline val red get() = vector.y
    inline val green get() = vector.z
    inline val blue get() = vector.w
    inline val rgb get() = vector.yzw
    inline val rgba get() = RGBA(red, green, blue, alpha)


    override fun toString() = "ARGB(a=$alpha, r=$red, g=$green, b=$blue)"

}


