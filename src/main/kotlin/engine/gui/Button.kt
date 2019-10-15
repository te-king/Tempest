package engine.gui

import math.RGBA
import wrappers.nanovg.Surface

class Button : Element() {

    var border = RGBA(1f, 0f, 0f, 1f)
    var background = RGBA(0f, 1f, 0f, 1f)
    var foreground = RGBA(0f, 0f, 1f, 1f)

    var borderHighlighted = RGBA(0f, 1f, 0f, 1f)
    var backgroundHighlighted = RGBA(1f, 0f, 0f, 1f)
    var foregroundHighlighted = RGBA(0f, 0f, 1f, 1f)

    override fun draw(surface: Surface) {

        if (surface.cursorOver) {

            // Border
            surface.fillRectangle(borderHighlighted)

            // Background
            surface.push(1, 1, surface.width - 2, surface.height - 2)
            surface.fillRectangle(backgroundHighlighted)
            surface.pop()

            // Text
            surface.text("Hello", foregroundHighlighted)

        } else {

            // Border
            surface.fillRectangle(border)

            // Background
            surface.push(1, 1, surface.width - 2, surface.height - 2)
            surface.fillRectangle(background)
            surface.pop()

            // Text
            surface.text("Hello", foreground)

        }

    }

}