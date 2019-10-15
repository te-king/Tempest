package engine.runtime

import engine.world.Scene
import engine.world.Updatable
import extensions.findAll
import math.Float2
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL45.*
import wrappers.glfw.Window
import wrappers.opengl.Device
import kotlin.time.ExperimentalTime
import kotlin.time.MonoClock

object Client {

    val window = run {
        glfwInit()
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
        Window(640, 480, "New Window", null, null)
    }

    val device = run {
        window.makeContextCurrent()
        Device(GL.createCapabilities())
    }

    init {
        window.onKeyPress { _, key, action, _ ->
            Input.keys[key] = action != GLFW_RELEASE
        }

        window.onMouseMove { _, x, y ->
            Input.cursor = Float2(x.toFloat(), y.toFloat())
        }
    }


    // Scene
    var scene = Scene(device)


    @ExperimentalTime
    fun run() {
        glEnable(GL_DEPTH_TEST)
        glEnable(GL_CULL_FACE)


        var mark = MonoClock.markNow()

        window.loop {

            // Update all nodes in the scene
            for (updatable in scene findAll Updatable::class)
                updatable.update(mark.elapsedNow().inSeconds.toFloat())

            // Complete all tasks of the device
            device.executeCommandQueue()

            // Display Back Buffer
            window.swapBuffers()

            // Update Clock
            mark = MonoClock.markNow()

            Thread.sleep(1)

        }

    }

}