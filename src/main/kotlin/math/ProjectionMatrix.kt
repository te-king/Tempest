package math

import kotlin.math.PI
import kotlin.math.tan

inline class ProjectionMatrix(val matrix: Float4x4) {

    companion object {
        val identity = ProjectionMatrix(Float4x4.identity)

        fun perspectiveOffCenter(left: Float, right: Float, bottom: Float, top: Float, zNear: Float, zFar: Float): ProjectionMatrix {
            val x = 2.0f * zNear / (right - left)
            val y = 2.0f * zNear / (top - bottom)
            val a = (right + left) / (right - left)
            val b = (top + bottom) / (top - bottom)
            val c = -(zFar + zNear) / (zFar - zNear)
            val d = -(2.0f * zFar * zNear) / (zFar - zNear)

            return ProjectionMatrix(Float4x4(
                    x, 0f, 0f, 0f,
                    0f, y, 0f, 0f,
                    a, b, c, -1f,
                    0f, 0f, d, 0f
            ))
        }

        fun perspectiveFieldOfView(fovy: Float, aspect: Float, zNear: Float, zFar: Float): ProjectionMatrix {
            if (fovy <= 0 || fovy > PI)
                throw IllegalArgumentException("fovy")
            if (aspect <= 0)
                throw IllegalArgumentException("aspect")

            val yMax = zNear * tan(0.5f * fovy)
            val yMin = -yMax
            val xMin = yMin * aspect
            val xMax = yMax * aspect

            return perspectiveOffCenter(xMin, xMax, yMin, yMax, zNear, zFar)
        }

    }


    operator fun times(other: TransformationMatrix) = matrix * other.matrix
    operator fun times(other: Float4x4) = matrix * other
    operator fun times(other: Float4) = matrix * other

}