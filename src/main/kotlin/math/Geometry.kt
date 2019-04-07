package math

sealed class Geometry {

    class Sphere(val radius: Float): Geometry()

    class Box(val size: Float3)



    class Smoothing(val g0: Geometry, val radius: Float): Geometry()

    class Union(val g0: Geometry, val g1: Geometry): Geometry()

    class Subtraction(val g0: Geometry, val g1: Geometry): Geometry()

    class Intersection(val g0: Geometry, val g1: Geometry): Geometry()

}