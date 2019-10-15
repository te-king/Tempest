package wrappers.opengl

import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL46C.*
import org.lwjgl.opengl.GLCapabilities
import org.lwjgl.system.CustomBuffer
import wrappers.glfw.Window


class Device(val capabilities: GLCapabilities) {

    constructor(window: Window) : this(window.also { it.makeContextCurrent() }.let { GL.createCapabilities() })


    // Command List
    val commandQueue = mutableListOf<() -> Unit>()

    fun executeCommandQueue() {
        val copy = commandQueue.toList()
        commandQueue.clear()
        copy.forEach { it() }
        glFinish()
    }


    // Buffers
    fun buffer(size: Long, usage: BufferUsage): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, size, usage.native)
        // Resolves AMD GPUs not limiting clearing allocated memory
        glNamedBufferSubData(id, 0, ShortArray(size.toInt() / 2))
        return Buffer(this, id)
    }

    fun buffer(data: CustomBuffer<*>, usage: BufferUsage): Buffer {
        val id = glCreateBuffers()
        nglNamedBufferStorage(id, data.sizeof() * data.limit().toLong(), data.address(), usage.native)
        return Buffer(this, id)
    }

    fun buffer(data: ShortArray, usage: BufferUsage): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, usage.native)
        return Buffer(this, id)
    }

    fun buffer(data: IntArray, usage: BufferUsage): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, usage.native)
        return Buffer(this, id)
    }

    fun buffer(data: FloatArray, usage: BufferUsage): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, usage.native)
        return Buffer(this, id)
    }

    fun buffer(data: DoubleArray, usage: BufferUsage): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, usage.native)
        return Buffer(this, id)
    }


    // Framebuffers
    fun framebuffer(vararg textures: Pair<Int, Image2d>): Framebuffer {
        val id = glCreateFramebuffers()
        return Framebuffer(this, id, textures.toMap())
    }


    // Pipelines
    fun pipeline(vararg programs: Pair<ProgramType, Program>): Pipeline {
        val id = glCreateProgramPipelines()
        return Pipeline(this, id, programs.toMap())
    }


    // Programs
    fun program(type: ProgramType, source: String): Program {
        val id = glCreateShaderProgramv(type.native, source)
        return Program(this, id)
    }


    // Textures
    fun texture1d(levels: Int, internalFormat: TextureFormat, width: Int): Texture1d {
        val id = glCreateTextures(GL_TEXTURE_1D)
        glTextureStorage1D(id, levels, internalFormat.native, width)
        return Texture1d(this, id)
    }

    fun texture2d(levels: Int, internalFormat: TextureFormat, width: Int, height: Int): Texture2d {
        val id = glCreateTextures(GL_TEXTURE_2D)
        glTextureStorage2D(id, levels, internalFormat.native, width, height)
        return Texture2d(this, id)
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


    // Vertex Array
    fun vertexArray(): VertexArray {
        val id = glCreateVertexArrays()
        return VertexArray(this, id)
    }


    // Command Buffer
    inline fun enqueue(func: CommandBuffer.() -> Unit) {
        commandQueue.addAll(CommandBuffer().also(func).commands)
    }


    abstract class DeviceResource(val device: Device) {
        abstract fun delete()
        protected fun finalize() {
            device.commandQueue.add(::delete)
        }
    }

}

