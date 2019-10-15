package engine.gui

import math.Colors
import wrappers.nanovg.Surface

class Margin : Frame() {

    var left = 4
    var right = 4
    var top = 4
    var bottom = 4

    var background: Colors? = null


    override fun draw(surface: Surface) {
        background?.rgba?.let(surface::fillRectangle)
        surface.push(left, top, surface.width - left - right, surface.height - top - bottom)
        child?.draw(surface)
        surface.pop()
    }

}