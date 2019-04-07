@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

class Pipeline(device: Device, val id: Int): Device.DeviceResource(device) {

    val infoLog get() = glGetProgramPipelineInfoLog(id)

    override fun delete() = glDeleteProgramPipelines(id)

}