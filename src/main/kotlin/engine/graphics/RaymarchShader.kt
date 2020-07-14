package engine.graphics

import engine.runtime.Client
import extensions.sizeOf
import math.Float3
import opengl.*

object RaymarchShader {

    private val device get() = Client.device

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(device, VertexProgram)
    private val fragmentProgram = resourceAt("""shaders/RaymarchFragmentShader.glsl""").loadShaderSource(device, FragmentProgram)

    val pipeline = device.pipeline(vertexProgram, fragmentProgram)
    val layout = device.vertexArray(0 to Float3::class, 1 to Float3::class, 2 to Float3::class, 3 to Float3::class)

    init {
        println(vertexProgram.infoLog)
        println(fragmentProgram.infoLog)
        println(pipeline.infoLog)
    }


    class Material : engine.graphics.Material {

        override val buffer = device.buffer(sizeOf(Float::class), UniformBuffer, DynamicStorage)

        override val pipeline get() = RaymarchShader.pipeline

        override val layout get() = RaymarchShader.layout


        var radius = 0f
            set(value) {
                buffer.setSubData(0, floatArrayOf(value))
                field = value
            }

    }

}