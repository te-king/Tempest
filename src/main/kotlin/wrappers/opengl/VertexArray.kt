@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "NOTHING_TO_INLINE")

package wrappers.opengl

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.lwjgl.opengl.GL46C.*

class VertexArray(val device: Device, val id: Int) {

    protected fun finalize() {
        GlobalScope.launch(device.context) {
            glDeleteVertexArrays(id)
        }
    }

}