package engine.gui.components

import engine.gui.*
import engine.runtime.*
import math.*
import org.lwjgl.nanovg.*

class Button(var text: String = "Button") : Element {


    var border = RGBA.border
    var background = RGBA.background
    var foreground = RGBA.foreground

    var hoverHighlight = RGBA(RGBA.accentLight.red, RGBA.accentLight.green, RGBA.accentLight.blue, 0.1f)
    var clickHighlight = RGBA(RGBA.accentDark.red, RGBA.accentDark.green, RGBA.accentDark.blue, 0.1f)

    override fun draw(surface: Surface) {

        // Border
        surface.fillRectangle(border)

        // Background
        surface.push(1, 1, surface.width - 2, surface.height - 2)
        surface.fillRectangle(background)
        surface.pop()

        // Text
        surface.text(text, foreground, NanoVG.NVG_ALIGN_CENTER or NanoVG.NVG_ALIGN_MIDDLE)

        if (surface.cursorOver) surface.fillRectangle(hoverHighlight)
//        if (surface.cursorOver and (Input.mouse[0] == true)) surface.fillRectangle(clickHighlight)

    }

}