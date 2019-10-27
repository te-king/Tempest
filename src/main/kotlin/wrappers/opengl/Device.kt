package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL46C.*
import org.lwjgl.system.*
import wrappers.glfw.*


inline class Device(val context: CoroutineDispatcher) {

    constructor(window: Window) : this(window.context)


    // Buffers
    fun buffer(size: Long, usage: BufferUsage) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, size, usage.native)
            glNamedBufferSubData(id, 0, ShortArray(size.toInt() / 2))
            Buffer(this@Device, id)
        }

    fun buffer(data: CustomBuffer<*>, usage: BufferUsage) =
        runBlocking(context) {
            val id = glCreateBuffers()
            nglNamedBufferStorage(id, data.sizeof() * data.limit().toLong(), data.address(), usage.native)
            Buffer(this@Device, id)
        }

    fun buffer(data: ShortArray, usage: BufferUsage) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, usage.native)
            Buffer(this@Device, id)
        }

    fun buffer(data: IntArray, usage: BufferUsage) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, usage.native)
            Buffer(this@Device, id)
        }

    fun buffer(data: FloatArray, usage: BufferUsage) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, usage.native)
            Buffer(this@Device, id)
        }

    fun buffer(data: DoubleArray, usage: BufferUsage) =
        runBlocking(context) {
            val id = glCreateBuffers()
            glNamedBufferStorage(id, data, usage.native)
            Buffer(this@Device, id)
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
            Texture1d(this@Device, id)
        }

    fun texture2d(levels: Int, internalFormat: TextureFormat, width: Int, height: Int) =
        runBlocking(context) {
            val id = glCreateTextures(GL_TEXTURE_2D)
            glTextureStorage2D(id, levels, internalFormat.native, width, height)
            Texture2d(this@Device, id)
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
    inline fun enqueue(crossinline func: CommandBuffer.() -> Unit) =
        runBlocking(context) {
            CommandBuffer().also(func).commands.forEach { it.invoke() }
        }

}

