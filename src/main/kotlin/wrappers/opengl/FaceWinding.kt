package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class FaceWinding(val native: Int) {
    CounterClockWise(GL_CCW),
    ClockWise(GL_CW)
}
