package wrappers.opengl

import org.lwjgl.opengl.GL45.*
import org.lwjgl.opengl.GLCapabilities


class Device (val capabilities: GLCapabilities) {

    // Command List
    private val commandQueue = mutableListOf<() -> Unit>()

    fun executeCommandQueue() {
        commandQueue.forEach { it() }
        commandQueue.clear()
        glFinish()
    }


    // Resource factory
    fun buffer() = object: Buffer() {
        override val device = this@Device
        override val id = glCreateBuffers()
        fun finalize() { commandQueue.add(::delete) }
    }

    fun texture(textureTarget: TextureTarget) = object: Texture() {
        override val device = this@Device
        override val id = glCreateTextures(textureTarget.native)
        override val target = textureTarget
        fun finalize() { commandQueue.add(::delete) }
    }

    fun pipeline() = object: Pipeline() {
        override val device = this@Device
        override val id = glCreateProgramPipelines()
        fun finalize() { commandQueue.add(::delete) }
    }

    fun framebuffer() = object: Framebuffer() {
        override val device = this@Device
        override val id = glCreateFramebuffers()
        fun finalize() { commandQueue.add(::delete) }
    }

    fun program(type: ProgramType, source: String) = object: Program() {
        override val device = this@Device
        override val id = glCreateShaderProgramv(type.native, source)
        fun finalize() { commandQueue.add(::delete) }
    }

    fun vertexArray() = object: VertexArray() {
        override val device = this@Device
        override val id = glCreateVertexArrays()
        fun finalize() { commandQueue.add(::delete) }
    }

    fun createRenderCommandBuffer() = RenderCommandBuffer(this)
    fun createBlitCommandBuffer() = BlitCommandBuffer(this)

    abstract class CommandBuffer(val device: Device) {
        protected val commands = mutableListOf<() -> Unit>()
        fun submit() { device.commandQueue.addAll(commands) }
    }

}

