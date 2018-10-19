package wrappers.opengl

import org.lwjgl.opengl.GL45.*

class RenderCommandBuffer(device: Device): Device.CommandBuffer(device) {

    fun clearFramebuffer() = commands.add { glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT) }

    fun setCullMode(mode: CullMode) = commands.add { glCullFace(mode.native) }
    fun setFrontFace(winding: FaceWinding) = commands.add { glFrontFace(winding.native) }
    fun setTriangleFillMode(mode: TriangleFillMode) = commands.add { glPolygonMode(GL_FRONT_AND_BACK, mode.native) }

    fun bindStorageBuffer(index: Int, buffer: Buffer) = commands.add { glBindBufferBase(GL_SHADER_STORAGE_BUFFER, index, buffer.id) }
    fun bindUniformBuffer(index: Int, buffer: Buffer) = commands.add { glBindBufferBase(GL_UNIFORM_BUFFER, index, buffer.id) }

    fun bindBuffer(target: BufferTarget, buffer: Buffer?) = commands.add { glBindBuffer(target.native, buffer?.id ?: 0) }
    fun bindTexture(index: Int, texture: Texture?) = commands.add { glBindTextureUnit(index, texture?.id ?: 0) }
    fun bindPipeline(renderPipeline: Pipeline?) = commands.add { glBindProgramPipeline(renderPipeline?.id ?: 0) }
    fun bindVertexArray(vertexArray: VertexArray?) = commands.add { glBindVertexArray(vertexArray?.id ?: 0) }
    fun bindFramebuffer(framebuffer: Framebuffer?) = commands.add { glBindFramebuffer(GL_FRAMEBUFFER, framebuffer?.id ?: 0) }

    fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) = commands.add { glDrawArrays(primitiveType.native, 0, vertexCount) }
    fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int, indexType: IndexType) = commands.add { glDrawElements(primitiveType.native, indexCount, indexType.native, 0) }

}

