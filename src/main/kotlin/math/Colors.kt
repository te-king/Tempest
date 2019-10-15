package math

enum class Colors(val r: Float, val g: Float, val b: Float, val a: Float) {

    Red(1f, 0f, 0f, 1f),
    Green(0f, 1f, 0f, 1f),
    Blue(0f, 0f, 1f, 1f),

    Black(0f, 0f, 0f, 1f),
    White(1f, 1f, 1f, 1f),
    Transparent(0f, 0f, 0f, 1f);

    inline val rgb get() = RGB(r, g, b)
    inline val rgba get() = RGBA(r, g, b, a)
    inline val argb get() = ARGB(a, r, g, b)

}