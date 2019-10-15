package wrappers.glfw

import engine.runtime.Key
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWCursorPosCallback
import org.lwjgl.glfw.GLFWCursorPosCallbackI

class Window private constructor(val handle: GLFWwindow) {

    constructor(width: Int, height: Int, title: String, monitor: Monitor? = null, share: Window? = null) : this(glfwCreateWindow(width, height, "", monitor?.handle ?: 0, share?.handle ?: 0)) {
        this.title = title
    }


    var title: String = ""
        set(value) {
            glfwSetWindowTitle(handle, value)
            field = value
        }

    var x: Int
        get() = intArrayOf(0).apply { glfwGetWindowPos(handle, this, null) }[0]
        set(value) = glfwSetWindowPos(handle, value, y)

    var y: Int
        get() = intArrayOf(0).apply { glfwGetWindowPos(handle, null, this) }[0]
        set(value) = glfwSetWindowPos(handle, x, value)

    var width: Int
        get() = intArrayOf(0).apply { glfwGetWindowSize(handle, this, null) }[0]
        set(value) = glfwSetWindowSize(handle, value, height)

    var height: Int
        get() = intArrayOf(0).apply { glfwGetWindowSize(handle, null, this) }[0]
        set(value) = glfwSetWindowSize(handle, width, value)


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


    inline fun loop(func: () -> Unit) {
        while (!glfwWindowShouldClose(handle)) {
            func()
            glfwPollEvents()
        }
    }

    fun makeContextCurrent() = glfwMakeContextCurrent(handle)

    fun swapBuffers() = glfwSwapBuffers(handle)

    fun delete() = glfwDestroyWindow(handle)

}