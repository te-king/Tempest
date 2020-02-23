package opengl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import math.*
import org.lwjgl.opengl.GL46C.*
import org.lwjgl.system.CustomBuffer
import glfw.Window
import kotlin.reflect.KClass


class Device(val context: CoroutineDispatcher) {

    constructor(window: Window) : this(window.context)

    // Constants
    val defaultFramebuffer = Framebuffer(this, 0, mapOf())

    val defaultVertexArray = VertexArray(this, 0)


    // Buffers
    fun <T : BufferTarget, S : StorageKind> buffer(size: Long, target: T, storage: S) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, size, storage.bufferBits)
            glNamedBufferSubData(id, 0, ShortArray(size.toInt() / 2))
            Buffer(this@Device, id, size, target, storage)
        }

    fun <T : BufferTarget, S : StorageKind> buffer(data: ShortArray, target: T, storage: S) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, storage.bufferBits)
            Buffer(this@Device, id, Short.SIZE_BYTES * data.size.toLong(), target, storage)
        }

    fun <T : BufferTarget, S : StorageKind> buffer(data: IntArray, target: T, storage: S) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, storage.bufferBits)
            Buffer(this@Device, id, Int.SIZE_BYTES * data.size.toLong(), target, storage)
        }

    fun <T : BufferTarget, S : StorageKind> buffer(data: FloatArray, target: T, storage: S) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, storage.bufferBits)
            Buffer(this@Device, id, Int.SIZE_BYTES * data.size.toLong(), target, storage)
        }

    fun <T : BufferTarget, S : StorageKind> buffer(data: DoubleArray, target: T, storage: S) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, storage.bufferBits)
            Buffer(this@Device, id, Long.SIZE_BYTES * data.size.toLong(), target, storage)
        }

    fun <T : BufferTarget, S : StorageKind> buffer(data: CustomBuffer<*>, target: T, storage: S) =
        runBlocking(context) {
            val id = glCreateBuffers()
            nglNamedBufferStorage(id, data.sizeof() * data.limit().toLong(), data.address(), storage.bufferBits)
            Buffer(this@Device, id, data.sizeof() * data.limit().toLong(), target, storage)
        }


    // Framebuffers
    fun framebuffer(vararg textures: Pair<FramebufferAttachment, Image<*, Texture2d>>) =
        runBlocking(context) {
            val id = glCreateFramebuffers()
            for (it in textures) glNamedFramebufferTexture(id, it.first.native, it.second.texture.id, it.second.index)
            Framebuffer(this@Device, id, textures.toMap())
        }


    // Pipelines
    fun pipeline(vertexProgram: Program<VertexProgram>, fragmentProgram: Program<FragmentProgram>) =
        runBlocking(context) {
            val id = glCreateProgramPipelines()
            glUseProgramStages(id, VertexProgram.bit, vertexProgram.id)
            glUseProgramStages(id, FragmentProgram.bit, fragmentProgram.id)
            Pipeline(this@Device, id, vertexProgram, fragmentProgram)
        }


    // Programs
    fun <P : ProgramKind> program(source: String, type: P) =
        runBlocking(context) {
            val id = glCreateShaderProgramv(type.native, source)
            Program(this@Device, id, type)
        }


    // Textures
    fun <F : TextureFormat> texture2d(levels: Int, width: Int, height: Int, internalFormat: F) =
        runBlocking(context) {
            val id = glCreateTextures(GL_TEXTURE_2D)
            glTextureStorage2D(id, levels, internalFormat.native, width, height)
            Texture(this@Device, id, levels, width, height, 1, internalFormat, Texture2d)
        }

    fun <F : TextureFormat> image2d(width: Int, height: Int, internalFormat: F) = texture2d(1, width, height, internalFormat)[0]


    // Vertex Array
    fun vertexArray(vararg pairs: Pair<Int, KClass<*>>) =
        runBlocking(context) {
            val id = glCreateVertexArrays()
            for (pair in pairs) {
                glVertexArrayAttribBinding(id, pair.first, pair.first)
                when (pair.second) {
                    Float::class -> glVertexArrayAttribFormat(id, pair.first, 1, GL_FLOAT, false, 0)
                    Float2::class -> glVertexArrayAttribFormat(id, pair.first, 2, GL_FLOAT, false, 0)
                    Float3::class -> glVertexArrayAttribFormat(id, pair.first, 3, GL_FLOAT, false, 0)
                    Float4::class -> glVertexArrayAttribFormat(id, pair.first, 4, GL_FLOAT, false, 0)
                    Int::class -> glVertexArrayAttribIFormat(id, pair.first, 1, GL_INT, 0)
                    Int2::class -> glVertexArrayAttribIFormat(id, pair.first, 2, GL_INT, 0)
                    Int3::class -> glVertexArrayAttribIFormat(id, pair.first, 3, GL_INT, 0)
                    Int4::class -> glVertexArrayAttribIFormat(id, pair.first, 4, GL_INT, 0)
                }
            }
            VertexArray(this@Device, id)
        }


    // Command Buffer
    inline fun enqueue(state: DeviceState, crossinline func: CommandBuffer.() -> Unit) {

        val cb = CommandBuffer().also(func).commands

        runBlocking(context) {
            glBindFramebuffer(GL_READ_FRAMEBUFFER, state.readFramebuffer?.id ?: 0)
            glBindFramebuffer(GL_DRAW_FRAMEBUFFER, state.writeFramebuffer?.id ?: 0)

            glFrontFace(state.winding.native)

            if (state.cullFunc != null) {
                glEnable(GL_CULL_FACE)
                glCullFace(state.cullFunc.native)
            } else glDisable(GL_CULL_FACE)

            if (state.blendFunction != null) {
                glEnable(GL_BLEND)
                //
            } else glDisable(GL_BLEND)

            if (state.depthFunction != null) {
                glEnable(GL_DEPTH_TEST)
                glDepthFunc(state.depthFunction.native)
            } else glDisable(GL_DEPTH_TEST)

            if (state.stencilFunc != null) {
                glEnable(GL_STENCIL_TEST)
                glStencilMask(state.stencilMask.toInt())
                //
                //
            } else glDisable(GL_STENCIL_TEST)

            cb.forEach { it.invoke() }
        }

    }

}

