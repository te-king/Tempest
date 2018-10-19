package wrappers.glfw

import org.lwjgl.glfw.GLFW.*
import java.nio.IntBuffer

data class Window(val window: Long) {

    constructor(width: Int, height: Int, title: String, monitor: Long? = null, share: Window? = null): this(glfwCreateWindow(width, height, title, monitor ?: 0, share?.window ?: 0))

    fun destroy() = glfwDestroyWindow(window)
    fun shouldClose() = glfwWindowShouldClose(window)
    fun setShouldClose(value: Boolean) = glfwSetWindowShouldClose(window, value)
    fun setTitle(title: String) = glfwSetWindowTitle(window, title)
    //fun setIcon()
    fun getPos(xpos: IntArray, ypos: IntArray) = glfwGetWindowPos(window, xpos, ypos)
    fun getPos(xpos: IntBuffer, ypos: IntBuffer) = glfwGetWindowPos(window, xpos, ypos)
    fun setPos(xpos: Int, ypos: Int) = glfwSetWindowPos(window, xpos, ypos)
    // fun getSize
    /*

     */
    fun makeContextCurrent() = glfwMakeContextCurrent(window)
    fun swapBuffers() = glfwSwapBuffers(window)

    //fun setKeyCallback(cbfun: (window: Long, key: Int, scanCode: Int, action: Int, mods: Int) -> Unit) = glfwSetKeyCallback(window, cbfun)

}