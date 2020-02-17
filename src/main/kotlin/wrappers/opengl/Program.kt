package wrappers.opengl

import kotlinx.coroutines.*
import org.lwjgl.opengl.GL46C.*

class Program<P : ProgramKind>(val device: Device, val id: Int, val kind: P) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glGetProgramInfoLog(id)
        }
    }

}

val Program<*>.infoLog: String
    get() = runBlocking(device.context) {
        glGetProgramInfoLog(id)
    }