package engine.graphics

import math.Float4
import org.lwjgl.opengl.GL45.GL_DYNAMIC_STORAGE_BIT
import wrappers.opengl.Device
import wrappers.opengl.ProgramType
import wrappers.opengl.Texture2d

class StandardObjectShader(val device: Device) {

    private val vertexProgram = resourceAt("""shaders/StandardVertexShader.glsl""").loadShaderSource(device, ProgramType.VERTEX)
    private val fragmentProgram = resourceAt("""shaders/StandardFragmentShader.glsl""").loadShaderSource(device, ProgramType.FRAGMENT)

    val pipeline = device.pipeline().apply {
        setStage(ProgramType.VERTEX, vertexProgram)
        setStage(ProgramType.FRAGMENT, fragmentProgram)
    }

    inner class Material: engine.graphics.Material {

        override val buffer = device.buffer().apply { allocateImmutable(Float4.SIZE_BYTES * Long.SIZE_BYTES.toLong() * 2, GL_DYNAMIC_STORAGE_BIT) }

        override val pipeline get() = this@StandardObjectShader.pipeline


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