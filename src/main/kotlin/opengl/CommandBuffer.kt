package opengl

import engine.graphics.*
import engine.gui.*
import engine.gui.components.*
import math.*
import org.lwjgl.opengl.GL46C.*


class CommandBuffer(val commands: MutableList<() -> Unit> = mutableListOf()) {

    // Binding
    fun bindUniformBuffer(index: Int, buffer: Buffer<UniformBuffer, *>?) =
            commands.add {
                glBindBufferBase(GL_UNIFORM_BUFFER, index, buffer?.id ?: 0)
            }

    fun bindVertexBuffer(index: Int, buffer: Buffer<*, *>?, offset: Long, stride: Int) =
            commands.add {
                if (buffer != null) glEnableVertexAttribArray(index)
                else glDisableVertexAttribArray(index)
                glBindVertexBuffer(index, buffer?.id ?: 0, offset, stride)
            }

    fun bindVertexBuffer(index: Int, vertexBuffer: Mesh.VertexBuffer?) =
            bindVertexBuffer(index, vertexBuffer?.buffer, vertexBuffer?.offset ?: 0, vertexBuffer?.stride ?: 0)

    fun bindElementBuffer(buffer: Buffer<*, *>?) =
            commands.add {
                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer?.id ?: 0)
            }

    fun bindElementBuffer(indexBuffer: Mesh.IndexBuffer?): Boolean {
        return bindElementBuffer(indexBuffer?.buffer)
    }

    fun bindPipeline(pipeline: Pipeline?) =
            commands.add {
                glBindProgramPipeline(pipeline?.id ?: 0)
            }

    fun bindVertexArray(vertexArray: VertexArray?) =
            commands.add {
                glBindVertexArray(vertexArray?.id ?: 0)
            }


    fun setTriangleFillMode(mode: TriangleFillMode) =
            commands.add {
                glPolygonMode(GL_FRONT_AND_BACK, mode.native)
            }

    fun clearFramebuffer() =
            commands.add {
                glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT or GL_STENCIL_BUFFER_BIT)
            }

    // Drawing
    fun drawPrimitives(primitiveType: PrimitiveType, vertexCount: Int) =
            commands.add {
                glDrawArrays(primitiveType.native, 0, vertexCount)
            }

    fun drawIndexedPrimitives(primitiveType: PrimitiveType, indexCount: Int, indexType: IndexType) =
            commands.add {
                glDrawElements(primitiveType.native, indexCount, indexType.native, 0)
            }

    // Blitting
    fun copyFramebuffer(src: Framebuffer, srcRect: Int4, dst: Framebuffer, dstRect: Int4, mask: CopyFramebufferMask, filter: CopyFramebufferFilter) =
            commands.add {
                glBlitNamedFramebuffer(
                        src.id,
                        dst.id,
                        srcRect.x, srcRect.y, srcRect.x + srcRect.z, srcRect.y + srcRect.w,
                        dstRect.x, dstRect.y, dstRect.x + dstRect.z, dstRect.y + dstRect.w,
                        mask.native,
                        filter.native
                )
            }

    // Gui
    fun paint(element: Element) {
        commands += {
            val surface = Surface(640f, 480f, 1.0f)
            element.draw(surface)
            surface.finalize()
        }
    }

}