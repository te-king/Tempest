package wrappers.opengl

import org.lwjgl.opengl.GL46C.*


sealed class StorageKind(val bufferBits: Int)
object ServerStorage : StorageKind(0)
object ClientStorage : StorageKind(GL_MAP_READ_BIT or GL_MAP_WRITE_BIT or GL_MAP_PERSISTENT_BIT or GL_CLIENT_STORAGE_BIT)
object DynamicStorage : StorageKind(GL_DYNAMIC_STORAGE_BIT)