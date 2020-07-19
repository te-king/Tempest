package engine.world.controllers

import engine.graphics.resourceAt
import engine.world.Scene
import extensions.sizeOf
import math.Float3
import opengl.*

class RaymarchShader(scene: Scene) : Controller(scene) {

    private val graphicsContext = controller<GraphicsContext>()

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(graphicsContext.device, VertexProgram)
    private val fragmentProgram = resourceAt("""shaders/RaymarchFragmentShader.glsl""").loadShaderSource(graphicsContext.device, FragmentProgram)


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


//    inner class Material : engine.graphics.Material {
//
//        override val buffer = graphicsContext.device.buffer(sizeOf(Float::class), UniformBuffer, DynamicStorage)
//        override val pipeline get() = this@RaymarchShader.pipeline
//        override val layout get() = this@RaymarchShader.layout
//
//
//        var radius = 0f
//            set(value) {
//                buffer.setSubData(0, floatArrayOf(value))
//                field = value
//            }
//
//    }

}