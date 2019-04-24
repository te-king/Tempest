package math

import kotlin.math.cos
import kotlin.math.sin

inline class TransformationMatrix(val matrix: Float4x4) {

    companion object {
        val identity = TransformationMatrix(Float4x4.identity)

        fun translation(x: Float, y: Float, z: Float) = TransformationMatrix(Float4x4(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            x, y, z, 1f
        ))

        fun translation(v: Float3) = translation(v.x, v.y, v.z)


        fun rotation(axis: Float3, angle: Float): TransformationMatrix {
            val cos = cos(-angle)
            val sin = sin(-angle)
            val t = 1.0f - cos

            val axis = axis.normalized

            return TransformationMatrix(Float4x4(
                t * axis.x * axis.x + cos, t * axis.x * axis.y - sin * axis.z, t * axis.x * axis.z + sin * axis.y, 0.0f,
                t * axis.x * axis.y + sin * axis.z, t * axis.y * axis.y + cos, t * axis.y * axis.z - sin * axis.x, 0.0f,
                t * axis.x * axis.z - sin * axis.y, t * axis.y * axis.z + sin * axis.x, t * axis.z * axis.z + cos, 0.0f,
                0f, 0f, 0f, 1f
            ))
        }

        fun rotation(q: Quaternion): TransformationMatrix {
            val axisAngle = q.axisAngle
            return rotation(Float3(axisAngle.x, axisAngle.y, axisAngle.z), axisAngle.w)
        }


        fun scaling(x: Float, y: Float, z: Float) = TransformationMatrix(Float4x4(
            x, 0f, 0f, 0f,
            0f, y, 0f, 0f,
            0f, 0f, z, 0f,
            0f, 0f, 0f, 1f
        ))

        fun scaling(v: Float3) = scaling(v.x, v.y, v.z)

    }


    // Can only be multiplied by another transformation matrix
    operator fun times(other: TransformationMatrix) = TransformationMatrix(matrix * other.matrix)
    operator fun times(other: Float4) = matrix * other

}