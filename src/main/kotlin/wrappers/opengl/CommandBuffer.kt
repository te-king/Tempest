package wrappers.opengl

import engine.graphics.Mesh
import engine.gui.Element
import math.Int4
import org.lwjgl.opengl.GL45.*
import wrappers.nanovg.Surface

abstract class CommandBuffer(val device: Device) {

    protected val commands = mutableListOf<() -> Unit>()


    // Binding
    fun bindBuffer(index: Int, buffer: Buffer?) {
        val id = buffer?.id ?: 0
        commands += { glBindBufferBase(GL_UNIFORM_BUFFER, index, id) }
    }

    fun bindPipeline(pipeline: Pipeline?) {
        val id = pipeline?.id ?: 0
        commands += { glBindProgramPipeline(id) }
    }

    fun bindVertexArray(vertexArray: VertexArray?) {
        val id = vertexArray?.id ?: 0
        commands += { glBindVertexArray(id) }
    }

    fun bindFramebuffer(framebuffer: Framebuffer?) {
        val id = framebuffer?.id ?: 0
        commands += { glBindFramebuffer(GL_FRAMEBUFFER, id) }
    }

    fun bindVertexBuffer(index: Int, vertexBuffer: Mesh.VertexBuffer?) {
        commands += if (vertexBuffer == null) {
            {
                glDisableVertexAttribArray(index)
            }
        } else {
            {
                glEnableVertexAttribArray(index)
                glBindVertexBuffer(index, vertexBuffer.buffer.id, vertexBuffer.offset, vertexBuffer.stride)
            }
        }
    }

    fun bindElementBuffer(indexBuffer: Mesh.IndexBuffer?) {
        val id = indexBuffer?.buffer?.id ?: 0
        commands += { glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id) }
    }

    // Context management
    fun setDepthFunc(func: DepthFunc) {
        commands += { glDepthFunc(func.native) }
    }

    fun setCullMode(mode: CullMode) {
        commands += { glCullFace(mode.native) }
    }

    fun setFrontFace(winding: FaceWinding) {
        commands += { glFrontFace(winding.native) }
    }

    fun setTriangleFillMode(mode: TriangleFillMode) {
        commands += { glPolygonMode(GL_FRONT_AND_BACK, mode.native) }
    }

    fun clearFramebuffer() {
        commands += { glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT or GL_STENCIL_BUFFER_BIT) }
    }

    // Drawing
    fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) {
        commands += { glDrawArrays(primitiveType.native, 0, vertexCount) }
    }

    fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int, indexType: IndexType) {
        commands += { glDrawElements(primitiveType.native, indexCount, indexType.native, 0) }
    }

    // Blitting
    fun copyFramebuffer(src: Framebuffer?, srcRect: Int4, dst: Framebuffer?, dstRect: Int4, mask: CopyFramebufferMask, filter: CopyFramebufferFilter) {
        commands += {
            glBlitNamedFramebuffer(
                src?.id ?: 0,
                dst?.id ?: 0,
                srcRect.x, srcRect.y, srcRect.x + srcRect.z, srcRect.y + srcRect.w,
                dstRect.x, dstRect.y, dstRect.x + dstRect.z, dstRect.y + dstRect.w,
                mask.native,
                filter.native
            )
        }
    }

    // Gui
    fun paint(element: Element) {
        commands += {
            // TODO: bruh
            val surface = Surface(640f, 480f, 1.0f)
            element.draw(surface)
            surface.finalize()
        }
    }


    abstract fun submit()

}