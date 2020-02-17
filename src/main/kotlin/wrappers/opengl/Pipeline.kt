package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Pipeline(val device: Device, val id: Int, val programs: List<Program<*>>) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteProgramPipelines(id)
        }
    }


    val infoLog: String
        get() = runBlocking(device.context) {
            glGetProgramPipelineInfoLog(id)
        }

}