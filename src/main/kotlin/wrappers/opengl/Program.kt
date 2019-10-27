package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Program(val device: Device, val id: Int) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glGetProgramInfoLog(id)
        }
    }


    val infoLog: String
        get() = runBlocking(device.context) {
            glGetProgramInfoLog(id)
        }

}