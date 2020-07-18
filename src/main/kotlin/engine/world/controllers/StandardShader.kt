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

        override val buffer = graphicsContext.device.buffer(sizeOf(Color::class, Long::class, Long::class), UniformBuffer, DynamicStorage)
        override val pipeline get() = this@StandardShader.pipeline
        override val layout get() = this@StandardShader.layout


        var diffuseColor = Color.transparent
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

    }


}