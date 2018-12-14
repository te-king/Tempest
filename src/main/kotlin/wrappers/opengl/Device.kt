package wrappers.opengl

import org.lwjgl.opengl.GL41
import org.lwjgl.opengl.GL45.*
import org.lwjgl.opengl.GLCapabilities
import org.lwjgl.system.CustomBuffer


class Device (val capabilities: GLCapabilities) {

    // Command List
    private val commandQueue = mutableListOf<() -> Unit>()

    fun executeCommandQueue() {
        val copy = commandQueue.toList()
        commandQueue.clear()
        copy.forEach { it() }
        glFinish()
    }


    // Buffers
    fun buffer(size: Long, flags: GLbitfield): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, size, flags)
        return Buffer(this, id)
    }

    fun buffer(data: CustomBuffer<*>, flags: GLbitfield): Buffer {
        val id = glCreateBuffers()
        nglNamedBufferStorage(id, data.sizeof() * data.limit().toLong(), data.address(), flags)
        return Buffer(this, id)
    }

    fun buffer(data: ShortArray, flags: GLbitfield): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, flags)
        return Buffer(this, id)
    }

    fun buffer(data: IntArray, flags: GLbitfield): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, flags)
        return Buffer(this, id)
    }

    fun buffer(data: FloatArray, flags: GLbitfield): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, flags)
        return Buffer(this, id)
    }

    fun buffer(data: DoubleArray, flags: GLbitfield): Buffer {
        val id = glCreateBuffers()
        glNamedBufferStorage(id, data, flags)
        return Buffer(this, id)
    }


    // Framebuffers
    fun framebuffer(vararg textures: Pair<Int, Texture>): Framebuffer {
        val id = glCreateFramebuffers()
        for ((key, value) in textures) glNamedFramebufferTexture(id, key, value.id, 0)
        glNamedFramebufferDrawBuffers(id, textures.map { it.first }.toIntArray())
        return Framebuffer(this, id)
    }


    // Pipelines
    fun pipeline(vararg programs: Pair<ProgramType, Program>): Pipeline {
        val id = glCreateProgramPipelines()
        programs.forEach { glUseProgramStages(id, it.first.bit, it.second.id) }
        return Pipeline(this, id)
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

    // Vertex Array
    fun vertexArray(): VertexArray {
        val id = glCreateVertexArrays()
        return VertexArray(this, id)
    }

    fun commandBuffer() = object: CommandBuffer(this) {
        override fun submit() { commandQueue.addAll(commands) }
    }


    abstract class DeviceResource(val device: Device) {
        abstract fun delete()
        protected fun finalize() { device.commandQueue.add(::delete) }
    }

    abstract class CommandBuffer2(val device: Device) {
        protected val queue = mutableListOf<() -> Unit>()
        fun submit() { device.commandQueue.addAll(queue) }
    }

}

