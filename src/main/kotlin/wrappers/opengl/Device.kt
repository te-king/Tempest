package wrappers.opengl

import org.lwjgl.opengl.GL45.*
import org.lwjgl.opengl.GLCapabilities


class Device (val capabilities: GLCapabilities) {

    // Command List
    private val commandQueue = mutableListOf<() -> Unit>()

    fun executeCommandQueue() {
        val copy = commandQueue.toList()
        commandQueue.clear()
        copy.forEach { it() }
        glFinish()
    }


    // Resource factory
    fun buffer() = object: Buffer(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun framebuffer() = object: Framebuffer(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun pipeline() = object: Pipeline(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun program(type: ProgramType, source: String) = object: Program(this, type, source) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun texture1d() = object: Texture1d(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun texture2d() = object: Texture2d(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun texture3d() = object: Texture3d(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun vertexArray() = object: VertexArray(this) {
        fun finalize() { commandQueue.add(::delete) }
    }

    fun commandBuffer() = object: CommandBuffer(this) {
        override fun submit() { commandQueue.addAll(commands) }
    }

}

