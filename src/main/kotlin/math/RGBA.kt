package math

inline class RGBA(val vector: Float4) {

    constructor(red: Float, green: Float, blue: Float, alpha: Float) : this(Float4(red, green, blue, alpha))


    inline val red get() = vector.x
    inline val green get() = vector.y
    inline val blue get() = vector.z
    inline val alpha get() = vector.w
    inline val rgb get() = vector.xyz
    inline val argb get() = ARGB(alpha, red, green, blue)


    override fun toString() = "RGBA(r=$red, g=$green, b=$blue, a=$alpha)"

}