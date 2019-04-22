package engine.graphics

import extensions.SIZE_BYTES
import org.lwjgl.opengl.GL11C
import wrappers.opengl.*

class RaymarchShader(val device: Device) {

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(device, ProgramType.VERTEX)
    private val fragmentProgram = resourceAt("""shaders/RaymarchFragmentShader.glsl""").loadShaderSource(device, ProgramType.FRAGMENT)

    val pipeline = device.pipeline(
        ProgramType.VERTEX to vertexProgram,
        ProgramType.FRAGMENT to fragmentProgram
    )

    val layout = device.vertexArray().apply {
        formatFloatAttribute(0, 3, GL11C.GL_FLOAT, false, 0)
        bindAttribute(0, 0)

        formatFloatAttribute(1, 3, GL11C.GL_FLOAT, false, 0)
        bindAttribute(1, 1)

        formatFloatAttribute(2, 3, GL11C.GL_FLOAT, false, 0)
        bindAttribute(2, 2)

        formatFloatAttribute(3, 3, GL11C.GL_FLOAT, false, 0)
        bindAttribute(3, 3)
    }

    init {
        println(vertexProgram.infoLog)
        println(fragmentProgram.infoLog)
        println(pipeline.infoLog)
    }


    inner class Material: engine.graphics.Material {

        override val buffer = device.buffer(Float.SIZE_BYTES.toLong(), BufferUsage.DYNAMIC)

        override val pipeline get() = this@RaymarchShader.pipeline

        override val layout get() = this@RaymarchShader.layout


        var radius = 0f
            set(value) {
                buffer.setSubData(0, floatArrayOf(value))
                field = value
            }

    }

}