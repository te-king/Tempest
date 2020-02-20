package engine.runtime

import engine.world.*
import extensions.*
import math.*
import org.lwjgl.glfw.GLFW.*
import wrappers.glfw.*
import wrappers.opengl.*
import kotlin.time.*

object Client {

    val window = run {
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
        Window(640, 480, "New Window")
    }

    val device = Device(window)

    init {
        window.onKeyPress { _, key, action, _ -> Input.keyboard[key] = action != GLFW_RELEASE }
        window.onMouseMove { _, x, y -> Input.cursor = Float2(x.toFloat(), y.toFloat()) }
        window.onMouseButton { _, button, action, _ -> Input.mouse[button] = action != GLFW_RELEASE }
    }


    // Scene
    var scene = Scene(device)


    @ExperimentalTime
    fun run() {

        var mark = MonoClock.markNow()

        window.loop {

            for (updatable in scene findAll Updatable::class)
                updatable.update(mark.elapsedNow().inSeconds.toFloat())

            window.swapBuffers()

            mark = MonoClock.markNow()
            Thread.sleep(1)

        }

    }

}