package math

inline class RGBA(val vector: Float4) {

    constructor(red: Float, green: Float, blue: Float, alpha: Float = 1f) : this(Float4(red, green, blue, alpha))

    constructor(red: Int, green: Int, blue: Int, alpha: Int = 255) : this(Float4(red.toFloat() / 255f, green.toFloat() / 255f, blue.toFloat() / 255f, alpha.toFloat() / 255f))


    inline val red get() = vector.x
    inline val green get() = vector.y
    inline val blue get() = vector.z
    inline val alpha get() = vector.w
    inline val rgb get() = vector.xyz
    inline val argb get() = ARGB(alpha, red, green, blue)


    override fun toString() = "RGBA(r=$red, g=$green, b=$blue, a=$alpha)"


    companion object {
        val red = RGBA(1f, 0f, 0f)
        val Green = RGBA(0f, 1f, 0f)
        val Blue = RGBA(0f, 0f, 1f)
        val Black = RGBA(0f, 0f, 0f)
        val White = RGBA(1f, 1f, 1f)
        val Transparent = RGBA(0f, 0f, 0f)


        val border = RGBA(143, 153, 173)
        val background = RGBA(39, 48, 67)
        val foreground = RGBA(237, 239, 244)
        val accentLight = RGBA(201, 147, 243)
        val accentDark = RGBA(4, 73, 221)
    }

}