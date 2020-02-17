package engine.graphics

import engine.runtime.Client
import math.Float4
import org.lwjgl.opengl.GL11C.GL_FLOAT
import wrappers.opengl.*

object StandardShader {

    private val device get() = Client.device

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(device, ProgramType.VERTEX)
    private val fragmentProgram = resourceAt("""shaders/StandardFragmentShader.glsl""").loadShaderSource(device, ProgramType.FRAGMENT)

    val pipeline = device.pipeline(
        ProgramType.VERTEX to vertexProgram,
        ProgramType.FRAGMENT to fragmentProgram
    )

    val layout = device.vertexArray().apply {
        formatFloatAttribute(0, 3, GL_FLOAT, false, 0)
        bindAttribute(0, 0)

        formatFloatAttribute(1, 3, GL_FLOAT, false, 0)
        bindAttribute(1, 1)

        formatFloatAttribute(2, 3, GL_FLOAT, false, 0)
        bindAttribute(2, 2)

        formatFloatAttribute(3, 3, GL_FLOAT, false, 0)
        bindAttribute(3, 3)
    }

    class Material: engine.graphics.Material {

        override val buffer = device.buffer(Float4.SIZE_BYTES * Long.SIZE_BYTES.toLong() * 2, UniformBuffer, DynamicStorage)

        override val pipeline get() = StandardShader.pipeline

        override val layout get() = StandardShader.layout


        var diffuseColor = Float4(0f, 0f, 0f, 0f)
            set(value) {
                buffer.setSubData(0, value.toFloatArray())
                field = value
            }

        var diffuseMap: Texture2d? = null
            set(value) {
                buffer.setSubData(Float4.SIZE_BYTES.toLong(), longArrayOf(value?.handle ?: 0))
                field = value
            }

        var normalMap: Texture2d? = null
            set(value) {
                buffer.setSubData(Float4.SIZE_BYTES.toLong() + Long.SIZE_BYTES.toLong(), longArrayOf(value?.handle ?: 0))
                field = value
            }

    }

}