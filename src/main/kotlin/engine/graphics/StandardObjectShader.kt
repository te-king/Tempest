package engine.graphics

import engine.runtime.Client
import wrappers.opengl.ProgramType
import wrappers.opengl.Texture
import math.Float4
import org.lwjgl.opengl.GL45.*
import java.io.File

object StandardObjectShader: ObjectShader(resourceAt("""shaders/StandardFragmentShader.glsl""").loadShaderSource(Client.device, ProgramType.FRAGMENT)) {
    /*Client.device.program(ProgramType.FRAGMENT, File("""shaders/StandardFragmentShader.glsl""").readText())*/

    class Material: engine.graphics.Material {

        override val pipeline get() = StandardObjectShader.pipeline

        override val buffer = Client.device.buffer().apply { allocateImmutable( Float4.SIZE_BYTES * Long.SIZE_BYTES.toLong() * 2, GL_DYNAMIC_STORAGE_BIT) }


        var diffuseColor = Float4(0f, 0f, 0f, 0f)
            set(value) {
                buffer.setSubData(0, value.toFloatArray())
                field = value
            }

        var diffuseMap: Texture? = null
            set(value) {
                buffer.setSubData(Float4.SIZE_BYTES.toLong(), longArrayOf(value?.handle ?: 0))
                field = value
            }

        var normalMap: Texture? = null
            set(value) {
                buffer.setSubData(Float4.SIZE_BYTES.toLong() + Long.SIZE_BYTES.toLong(), longArrayOf(value?.handle ?: 0))
                field = value
            }

    }

}