package wrappers.opengl

import kotlinx.coroutines.*
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
    fun framebuffer(vararg textures: Pair<Int, Image2d>) =
        runBlocking(context) {
            val id = glCreateFramebuffers()
            for (it in textures) glNamedFramebufferTexture(id, it.first, it.second.texture.id, it.second.index)
            Framebuffer(this@Device, id, textures.toMap())
        }


    // Pipelines
    fun pipeline(vararg programs: Pair<ProgramType, Program>) =
        runBlocking(context) {
            val id = glCreateProgramPipelines()
            for (it in programs) glUseProgramStages(id, it.first.bit, it.second.id)
            Pipeline(this@Device, id, programs.toMap())
        }


    // Programs
    fun program(type: ProgramType, source: String) =
        runBlocking(context) {
            val id = glCreateShaderProgramv(type.native, source)
            Program(this@Device, id)
        }


    // Textures
    fun texture1d(levels: Int, internalFormat: TextureFormat, width: Int) =
        runBlocking(context) {
            val id = glCreateTextures(GL_TEXTURE_1D)
            glTextureStorage1D(id, levels, internalFormat.native, width)
            Texture1d(this@Device, id, levels, internalFormat, width)
        }

    fun texture2d(levels: Int, internalFormat: TextureFormat, width: Int, height: Int) =
        runBlocking(context) {
            val id = glCreateTextures(GL_TEXTURE_2D)
            glTextureStorage2D(id, levels, internalFormat.native, width, height)
            Texture2d(this@Device, id, levels, internalFormat, width, height)
        }


    // Vertex Array
    fun vertexArray() =
        runBlocking(context) {
            val id = glCreateVertexArrays()
            VertexArray(this@Device, id)
        }


    // Images
    fun image1d(internalFormat: TextureFormat, width: Int): Image1d {
        val texture = texture1d(1, internalFormat, width)
        return Image1d(texture, 0)
    }

    fun image2d(internalFormat: TextureFormat, width: Int, height: Int): Image2d {
        val texture = texture2d(1, internalFormat, width, height)
        return Image2d(texture, 0)
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

