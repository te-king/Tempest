package math

inline class Color(val vector: Float4) {

    constructor(r: Float, g: Float, b: Float, a: Float) : this(Float4(r, g, b, a))


    inline val r get() = vector.x
    inline val g get() = vector.y
    inline val b get() = vector.z
    inline val a get() = vector.w


    companion object {

        val red = Color(1f, 0f, 0f, 1f)
        val green = Color(0f, 1f, 0f, 1f)
        val blue = Color(0f, 0f, 1f, 1f)

        val black = Color(0f, 0f, 0f, 1f)
        val white = Color(1f, 1f, 1f, 1f)

        val transparent = Color(0f, 0f, 0f, 1f)

    }

}