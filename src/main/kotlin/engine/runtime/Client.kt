package engine.runtime

import wrappers.opengl.Device
import wrappers.glfw.Window
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL45.*
import engine.world.*

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
        glfwSetKeyCallback(window.window) { window: Long, key: Int, scanCode: Int, action: Int, mods: Int ->
            Input.keys[Key.fromInt(key)!!] = action != GLFW_RELEASE
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
        while (!window.shouldClose()) {

            val newTime = System.nanoTime()
            val delta = (newTime - oldTime).toFloat() / 1_000_000_000f

            // Update all nodes in the scene
            for (node in scene)
                for (component in node)
                    (component as? Updatable)?.update(delta)

            // Complete all tasks of the device
            device.executeCommandQueue()

            oldTime = newTime
            frames++

            if (frames % 20 == 0L) window.setTitle("FPS: %.2f FRAMES: %s".format(1.0 / delta, frames))

            // Fire window events
            glfwPollEvents()

        }

    }

}