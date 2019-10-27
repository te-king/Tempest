package wrappers.glfw

import engine.runtime.*
import kotlinx.coroutines.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.*

class Window(width: Int, height: Int, title: String) {

    val handle = glfwCreateWindow(width, height, title, 0, 0)

    val context = newSingleThreadContext("OpenGL.Context")

    init {
        runBlocking(context) {
            glfwMakeContextCurrent(handle)
            GL.createCapabilities()
        }
    }


    val x get() = intArrayOf(0).apply { glfwGetWindowPos(handle, this, null) }[0]
    val y get() = intArrayOf(0).apply { glfwGetWindowPos(handle, null, this) }[0]
    val width get() = intArrayOf(0).apply { glfwGetWindowSize(handle, this, null) }[0]
    val height get() = intArrayOf(0).apply { glfwGetWindowSize(handle, null, this) }[0]


    fun onKeyPress(func: (window: Window, key: Key, action: Int, mods: Int) -> Unit) {
        glfwSetKeyCallback(handle) { _, key, _, action, mods ->
            func(this, Key.fromInt(key)!!, action, mods)
        }
    }

    fun onMouseMove(func: (window: Window, x: Double, y: Double) -> Unit) {
        glfwSetCursorPosCallback(handle) { _, x, y ->
            func(this, x, y)
        }
    }

    fun onMouseButton(func: (window: Window, button: Int, action: Int, mods: Int) -> Unit) {
        glfwSetMouseButtonCallback(handle) { _: Long, button: Int, action: Int, mods: Int ->
            func(this, button, action, mods)
        }
    }


    inline fun loop(func: () -> Unit) {
        while (!glfwWindowShouldClose(handle)) {
            func()
            glfwPollEvents()
        }
    }

    fun makeContextCurrent() = glfwMakeContextCurrent(handle)

    fun swapBuffers() = glfwSwapBuffers(handle)

    fun delete() = glfwDestroyWindow(handle)


    companion object {

        val initialized = glfwInit()

    }

}