package wrappers.opengl

import math.Int4
import org.lwjgl.opengl.GL45.*

abstract class CommandBuffer(val device: Device) {

    protected val commands = mutableListOf<() -> Unit>()


    // Binding
    fun bindStorageBuffer(index: Int, buffer: Buffer) = commands.add { glBindBufferBase(GL_SHADER_STORAGE_BUFFER, index, buffer.id) }
    fun bindUniformBuffer(index: Int, buffer: Buffer) = commands.add { glBindBufferBase(GL_UNIFORM_BUFFER, index, buffer.id) }
    fun bindTexture(index: Int, texture: Texture?) = commands.add { glBindTextureUnit(index, texture?.id ?: 0) }
    fun bindPipeline(renderPipeline: Pipeline?) = commands.add { glBindProgramPipeline(renderPipeline?.id ?: 0) }
    fun bindVertexArray(vertexArray: VertexArray?) = commands.add { glBindVertexArray(vertexArray?.id ?: 0) }
    fun bindFramebuffer(framebuffer: Framebuffer?) = commands.add { glBindFramebuffer(GL_FRAMEBUFFER, framebuffer?.id ?: 0) }

    // Context management
    fun setDepthFunc(func: DepthFunc) = commands.add { glDepthFunc(func.native) }
    fun setCullMode(mode: CullMode) = commands.add { glCullFace(mode.native) }
    fun setFrontFace(winding: FaceWinding) = commands.add { glFrontFace(winding.native) }
    fun setTriangleFillMode(mode: TriangleFillMode) = commands.add { glPolygonMode(GL_FRONT_AND_BACK, mode.native) }
    fun clearFramebuffer() = commands.add { glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT) }

    // Drawing
    fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) = commands.add { glDrawArrays(primitiveType.native, 0, vertexCount) }
    fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int, indexType: IndexType) = commands.add { glDrawElements(primitiveType.native, indexCount, indexType.native, 0) }

    // Blitting
    fun copyFramebuffer(src: Framebuffer?, srcRect: Int4, dst: Framebuffer?, dstRect: Int4, mask: CopyFramebufferMask, filter: CopyFramebufferFilter) = commands.add { glBlitNamedFramebuffer(src?.id ?: 0, dst?.id ?: 0, srcRect.x, srcRect.y, srcRect.x + srcRect.z, srcRect.y + srcRect.w, dstRect.x, dstRect.y, dstRect.x + dstRect.z, dstRect.y + dstRect.w, mask.native, filter.native) }


    abstract fun submit()

}

