package wrappers.opengl

import org.lwjgl.opengl.GL46C.*


sealed class BufferTarget(val native: Int)
object ArrayBuffer : BufferTarget(GL_ARRAY_BUFFER)
object AtomicCounterBuffer : BufferTarget(GL_ATOMIC_COUNTER_BUFFER)
object CopyReadBuffer : BufferTarget(GL_COPY_READ_BUFFER)
object CopyWriteBuffer : BufferTarget(GL_COPY_WRITE_BUFFER)
object DispatchIndirectBuffer : BufferTarget(GL_DISPATCH_INDIRECT_BUFFER)
object DrawIndirectBuffer : BufferTarget(GL_DRAW_INDIRECT_BUFFER)
object ElementArrayBuffer : BufferTarget(GL_ELEMENT_ARRAY_BUFFER)
object PixelPackBuffer : BufferTarget(GL_PIXEL_PACK_BUFFER)
object PixelUnpackBuffer : BufferTarget(GL_PIXEL_UNPACK_BUFFER)
object QueryBuffer : BufferTarget(GL_QUERY_BUFFER)
object ShaderStorageBuffer : BufferTarget(GL_SHADER_STORAGE_BUFFER)
object TextureBuffer : BufferTarget(GL_TEXTURE_BUFFER)
object TransformFeedbackBuffer : BufferTarget(GL_TRANSFORM_FEEDBACK_BUFFER)
object UniformBuffer : BufferTarget(GL_UNIFORM_BUFFER)