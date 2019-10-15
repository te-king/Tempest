package engine.gui.components

import engine.gui.*
import engine.runtime.*
import math.*
import org.lwjgl.nanovg.*

class Label(var text: String = "Button") : Element {

    var background = RGBA.background
    var foreground = RGBA.foreground

    override fun draw(surface: Surface) {
        surface.fillRectangle(background)
        surface.text(text, foreground, NanoVG.NVG_ALIGN_CENTER or NanoVG.NVG_ALIGN_MIDDLE)
    }

}