package engine.runtime

import engine.world.*
import extensions.*
import kotlinx.coroutines.*
import math.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL45.*
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

    val framebuffer = object : Framebuffer(device, 0, mapOf()) {
        override val width get() = window.width
        override val height get() = window.height
    }

    init {
        window.onKeyPress { _, key, action, _ -> Input.keyboard[key] = action != GLFW_RELEASE }
        window.onMouseMove { _, x, y -> Input.cursor = Float2(x.toFloat(), y.toFloat()) }
        window.onMouseButton { _, button, action, _ -> Input.mouse[button] = action != GLFW_RELEASE }
    }


    // Scene
    var scene = Scene(device)


    @ExperimentalTime
    fun run() {

        runBlocking(device.context) {
            glEnable(GL_DEPTH_TEST)
            glEnable(GL_CULL_FACE)
        }


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