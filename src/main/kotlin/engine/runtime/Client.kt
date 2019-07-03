package engine.runtime

import engine.graphics.RaymarchShader
import engine.graphics.StandardShader
import wrappers.opengl.Device
import wrappers.glfw.Window
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL45.*
import engine.world.*
import extensions.findAll

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
        window.onKeyPress { window, key, action, mods ->
            Input.keys[key] = action != GLFW_RELEASE
        }
    }


    // Scene
    var scene = Scene(device)


    fun run() {
        glEnable(GL_DEPTH_TEST)
        glEnable(GL_CULL_FACE)

        // Begin render loop
        var oldTime = System.nanoTime()
        var frames = 0L

        window.loop {

            val newTime = System.nanoTime()
            val delta = (newTime - oldTime).toFloat() / 1_000_000_000f

            //for (updatable in scene.nodes)

            // Update all nodes in the scene
            for (updatable in scene findAll Updatable::class)
                updatable.update(delta)

            // Complete all tasks of the device
            device.executeCommandQueue()

            oldTime = newTime
            frames++

            // Resolves AMD GPUs not limiting framerates
            Thread.sleep(2)

            if (frames % 20 == 0L) window.title = "FPS: %.2f FRAMES: %s".format(1.0 / delta, frames)

        }

    }

}