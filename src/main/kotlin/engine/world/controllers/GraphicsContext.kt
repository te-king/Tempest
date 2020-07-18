package engine.world.controllers


import engine.runtime.Client
import engine.runtime.Key
import engine.world.Scene
import engine.world.Updatable
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import opengl.Device
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL


class GraphicsContext(scene: Scene) : Controller(scene), Updatable {

    init {
        if (!glfwInit()) throw error("Failed to initialize glfw.")
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 5)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
    }


    private var handle = glfwCreateWindow(640, 480, "New Window", 0, 0)

    private val context = newSingleThreadContext("Glfw.OpenGL.Context")


    val device = Device(context)

    val keyboard = mutableMapOf<Key, Boolean>()

    val mouse = mutableMapOf<Int, Boolean>()


    init {
        runBlocking(context) {
            glfwMakeContextCurrent(handle)
            GL.createCapabilities()
        }

        glfwSetKeyCallback(handle) { _, key, _, action, mods ->
            keyboard[Key.fromInt(key)!!] = action != GLFW_RELEASE
        }

        glfwSetMouseButtonCallback(handle) { _: Long, button: Int, action: Int, mods: Int ->
            mouse[button] = action != GLFW_RELEASE
        }
    }


    fun swapBuffers() {
        glfwSwapBuffers(handle)
    }


    override fun update(delta: Double) {
        glfwPollEvents()
        Client.shouldClose = glfwWindowShouldClose(handle)
    }

}