@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

abstract class Pipeline {

    abstract val device: Device
    abstract val id: Int

    val infoLog get() = glGetProgramPipelineInfoLog(id)


    fun setStage(stage: ProgramType, value: Program?) = glUseProgramStages(id, stage.bit, value?.id ?: 0)

    fun delete() = glDeleteProgramPipelines(id)

}