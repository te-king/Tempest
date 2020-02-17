package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL41C
import org.lwjgl.opengl.GL46C.*
import org.lwjgl.system.*
import wrappers.glfw.*


inline class Device(val context: CoroutineDispatcher) {

    constructor(window: Window) : this(window.context)


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
    fun framebuffer(vararg textures: Pair<Int, Image<*, Texture2d>>) =
        runBlocking(context) {
            val id = glCreateFramebuffers()
            for (it in textures) glNamedFramebufferTexture(id, it.first, it.second.texture.id, it.second.index)
            Framebuffer(this@Device, id, textures.toMap())
        }


    // Pipelines
    fun pipeline(vertexProgram: Program<VertexProgram>, fragmentProgram: Program<FragmentProgram>) =
        runBlocking(context) {
            val id = glCreateProgramPipelines()
            GL41C.glUseProgramStages(id, VertexProgram.bit, vertexProgram.id)
            GL41C.glUseProgramStages(id, FragmentProgram.bit, fragmentProgram.id)
            Pipeline(this@Device, id, listOf(vertexProgram, fragmentProgram))
        }


    // Programs
    fun <P : ProgramKind> program(type: P, source: String) =
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
    fun vertexArray() =
        runBlocking(context) {
            val id = glCreateVertexArrays()
            VertexArray(this@Device, id)
        }


    // Command Buffer
    inline fun enqueue(state: DeviceState, crossinline func: CommandBuffer.() -> Unit) {

        val cb = CommandBuffer().also(func).commands

        runBlocking(context) {
            glBindFramebuffer(GL_FRAMEBUFFER, state.readFramebuffer?.id ?: 0)

            if (state.cull) glEnable(GL_CULL_FACE) else glDisable(GL_CULL_FACE)
            glCullFace(state.cullFunc.native)
            glFrontFace(state.winding.native)

            if (state.blend) glEnable(GL_BLEND) else glDisable(GL_BLEND)
            //

            if (state.depth) glEnable(GL_DEPTH_TEST) else glDisable(GL_DEPTH_TEST)
            glDepthFunc(state.depthFunction.native)

            if (state.stencil) glEnable(GL_STENCIL_TEST) else glDisable(GL_STENCIL_TEST)
            glStencilMask(state.stencilMask.toInt())
            //
            //

            cb.forEach { it.invoke() }

            glFinish()
        }

    }

}

