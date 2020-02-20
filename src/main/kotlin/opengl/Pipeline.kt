package opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Pipeline(val device: Device, val id: Int, val vertexProgram: Program<VertexProgram>, val fragmentProgram: Program<FragmentProgram>) {

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