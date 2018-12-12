@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

open class Pipeline(val device: Device) {

    val id = glCreateProgramPipelines()

    val infoLog get() = glGetProgramPipelineInfoLog(id)


    fun setStage(stage: ProgramType, value: Program?) = glUseProgramStages(id, stage.bit, value?.id ?: 0)

    fun delete() = glDeleteProgramPipelines(id)

}