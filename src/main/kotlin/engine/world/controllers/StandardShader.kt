package engine.world.controllers

import engine.graphics.resourceAt
import engine.world.Scene
import extensions.sizeOf
import math.Color
import math.Float3
import opengl.*


class StandardShader(scene: Scene) : Controller(scene) {

    private val graphicsContext = controller<GraphicsContext>()

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(graphicsContext.device, VertexProgram)
    private val fragmentProgram = resourceAt("""shaders/StandardFragmentShader.glsl""").loadShaderSource(graphicsContext.device, FragmentProgram)


    val pipeline = graphicsContext.device.pipeline(
        vertexProgram,
        fragmentProgram
    )

    val layout = graphicsContext.device.vertexArray(
        0 to Float3::class,
        1 to Float3::class,
        2 to Float3::class,
        3 to Float3::class
    )


    inner class Material : engine.graphics.Material {

        private val buffer = graphicsContext.device.buffer(sizeOf(Color::class, Long::class, Long::class), UniformBuffer, DynamicStorage)


        var diffuseColor = Color.red
            set(value) {
                buffer.setSubData(0, value.vector.toFloatArray())
                field = value
            }

        var diffuseMap: Texture<*, Texture2d>? = null
            set(value) {
                buffer.setSubData(sizeOf(Color::class), longArrayOf(value?.handle ?: 0))
                field = value
            }

        var normalMap: Texture<*, Texture2d>? = null
            set(value) {
                buffer.setSubData(sizeOf(Color::class, Long::class), longArrayOf(value?.handle ?: 0))
                field = value
            }


        override fun draw(buffer: CommandBuffer, mesh: MeshBuffer) {

            buffer.bindUniformBuffer(3, this.buffer)
            buffer.bindPipeline(pipeline)
            buffer.bindVertexArray(layout)

            for ((index, vertexBuffer) in mesh.vertexBuffers) {
                buffer.bindVertexBuffer(index, vertexBuffer)
            }

            mesh.elementBuffers.forEach { indexBuffer ->
                buffer.bindElementBuffer(indexBuffer)
                buffer.drawIndexedPrimitives(indexBuffer.primitiveType, indexBuffer.indexCount, indexBuffer.indexType)
            }

            for ((index, _) in mesh.vertexBuffers) {
                buffer.bindVertexBuffer(index, null)
            }

        }

        override fun draw(buffer: CommandBuffer, meshes: Sequence<MeshBuffer>) {

            buffer.bindUniformBuffer(3, this.buffer)
            buffer.bindPipeline(pipeline)
            buffer.bindVertexArray(layout)

            for (mesh in meshes) {

                for ((index, vertexBuffer) in mesh.vertexBuffers) {
                    buffer.bindVertexBuffer(index, vertexBuffer)
                }

                mesh.elementBuffers.forEach { indexBuffer ->
                    buffer.bindElementBuffer(indexBuffer)
                    buffer.drawIndexedPrimitives(indexBuffer.primitiveType, indexBuffer.indexCount, indexBuffer.indexType)
                }

                for ((index, _) in mesh.vertexBuffers) {
                    buffer.bindVertexBuffer(index, null)
                }

            }

        }

    }


}