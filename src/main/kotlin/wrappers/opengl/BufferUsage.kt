package wrappers.opengl

import org.lwjgl.opengl.GL46C.*

enum class BufferUsage(val native: Int) {
    SERVER_SIDE(0),
    CLIENT_SIDE(GL_MAP_READ_BIT or GL_MAP_WRITE_BIT or GL_MAP_PERSISTENT_BIT or GL_CLIENT_STORAGE_BIT),
    DYNAMIC(GL_DYNAMIC_STORAGE_BIT)
}
