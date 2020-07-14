package engine.graphics

import engine.runtime.Client
import extensions.sizeOf
import math.Color
import math.Float3
import opengl.*


object StandardShader {

    private val device get() = Client.device

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(device, VertexProgram)
    private val fragmentProgram = resourceAt("""shaders/StandardFragmentShader.glsl""").loadShaderSource(device, FragmentProgram)

    val pipeline = device.pipeline(
        vertexProgram,
        fragmentProgram
    )

    val layout = device.vertexArray(
        0 to Float3::class,
        1 to Float3::class,
        2 to Float3::class,
        3 to Float3::class
    )


    class Material : engine.graphics.Material {

        override val buffer = device.buffer(sizeOf(Color::class, Long::class, Long::class), UniformBuffer, DynamicStorage)
        override val pipeline get() = StandardShader.pipeline
        override val layout get() = StandardShader.layout


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