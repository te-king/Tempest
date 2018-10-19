package wrappers.opengl

import org.lwjgl.opengl.GL45.*

enum class PixelType(val native: kotlin.Int) {
    BYTE(GL_BYTE),
    UNSIGNED_BYTE(GL_UNSIGNED_BYTE),
    SHORT(GL_SHORT),
    UNSIGNED_SHORT(GL_UNSIGNED_SHORT),
    INT(GL_INT),
    UNSIGNED_INT(GL_UNSIGNED_INT),
    FLOAT(GL_FLOAT),
    DOUBLE(GL_DOUBLE)
}