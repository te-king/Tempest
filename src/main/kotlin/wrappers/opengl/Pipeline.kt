@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

class Pipeline(device: Device, val id: Int, private val programs: Map<ProgramType, Program>): Device.DeviceResource(device) {

    init {
        for (it in programs) glUseProgramStages(id, it.key.bit, it.value.id)
    }


    val infoLog get() = glGetProgramPipelineInfoLog(id)

    override fun delete() = glDeleteProgramPipelines(id)

}