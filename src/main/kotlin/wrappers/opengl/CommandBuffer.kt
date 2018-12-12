package wrappers.opengl

import engine.graphics.Mesh
import math.Int4
import org.lwjgl.opengl.GL45.*

abstract class CommandBuffer(val device: Device) {

    protected val commands = mutableListOf<() -> Unit>()


    // Binding
    fun bindBuffer(index: Int, buffer: Buffer?) {
        val id = buffer?.id ?: 0
        commands.add { glBindBufferBase(GL_UNIFORM_BUFFER, index, id) }
    }

    fun bindPipeline(pipeline: Pipeline?) {
        val id = pipeline?.id ?: 0
        commands.add { glBindProgramPipeline(id) }
    }

    fun bindVertexArray(vertexArray: VertexArray?) {
        val id = vertexArray?.id ?: 0
        commands.add { glBindVertexArray(id) }
    }

    fun bindFramebuffer(framebuffer: Framebuffer?) {
        val id = framebuffer?.id ?: 0
        commands.add { glBindFramebuffer(GL_FRAMEBUFFER, id) }
    }

    fun bindVertexBuffer(index: Int, vertexBuffer: Mesh.VertexBuffer?) {
        if (vertexBuffer == null)
            commands.add {
                glDisableVertexAttribArray(index)
            }
        else
            commands.add {
                glEnableVertexAttribArray(index)
                glBindVertexBuffer(index, vertexBuffer.buffer.id, vertexBuffer.offset, vertexBuffer.stride)
            }
    }

    fun bindElementBuffer(indexBuffer: Mesh.IndexBuffer?) {
        if (indexBuffer == null)
            commands.add {
                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
            }
        else
            commands.add {
                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer.buffer.id)
            }
    }

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