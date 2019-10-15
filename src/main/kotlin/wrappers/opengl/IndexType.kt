package wrappers.opengl

import org.lwjgl.opengl.GL46C.GL_UNSIGNED_INT
import org.lwjgl.opengl.GL46C.GL_UNSIGNED_SHORT

enum class IndexType(val native: Int) {
    UNSIGNED_SHORT(GL_UNSIGNED_SHORT),
    UNSIGNED_INT(GL_UNSIGNED_INT)
}
