package engine.gui

import engine.graphics.*
import engine.runtime.*
import math.*
import org.lwjgl.nanovg.*
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3.*
import wrappers.nanovg.*
import java.util.*
import kotlin.math.*


internal val nvgContext = nvgCreate(0)
internal val defaultFont = resourceAt("""assets/default.ttf""").loadFont("default") ?: throw Error("Default font failed to load")


class Surface(windowWidth: Float, windowHeight: Float, devicePixelRatio: Float) {

    private val stack = Stack<Int4>()

    val x get() = stack.peek()!!.x
    val y get() = stack.peek()!!.y
    val width get() = stack.peek()!!.z
    val height get() = stack.peek()!!.w

    val cursorOver: Boolean
        get() {
            return Input.cursor.x > x && Input.cursor.x < x + width &&
                    Input.cursor.y > y && Input.cursor.y < y + height
        }




    init {
        nvgBeginFrame(nvgContext, windowWidth, windowHeight, devicePixelRatio)
        stack += Int4(0, 0, windowWidth.toInt(), windowHeight.toInt())
    }


    fun fillRectangle(color: RGBA) {
        nvgBeginPath(nvgContext)
        nvgRect(nvgContext, x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat())
        color.nvg {
            nvgFillColor(nvgContext, it)
        }
        nvgFill(nvgContext)
    }

    fun text(str: String, color: RGBA, align: Int, font: Font = defaultFont) {
        nvgFontFaceId(nvgContext, font.id)
        color.nvg { nvgFillColor(nvgContext, it) }

        nvgTextAlign(nvgContext, NVG_ALIGN_CENTER or NVG_ALIGN_MIDDLE)
        nvgText(nvgContext, x.toFloat() + (width.toFloat() / 2), y.toFloat() + (height.toFloat() / 2), str)
    }


    fun push(x: Int, y: Int, width: Int, height: Int) {
        val absX = this.x + x
        val absY = this.y + y
        val absW = min(this.width - x, width)
        val absH = min(this.height - y, height)
        stack += Int4(absX, absY, absW, absH)
    }

    fun pop() {
        stack.pop()
    }


    fun finalize() {
        nvgEndFrame(nvgContext)
    }


    private inline fun RGBA.nvg(func: (NVGColor) -> Unit) {
        val color = NVGColor.create()
        nvgRGBAf(red, green, blue, alpha, color)
        func(color)
        color.free()
    }

}