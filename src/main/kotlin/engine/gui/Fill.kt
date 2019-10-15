package engine.gui

import math.Colors
import wrappers.nanovg.Surface

class Fill() : Element() {

    constructor(color: Colors) : this() {
        this.color = color
    }


    var color: Colors = Colors.Red

    override fun draw(surface: Surface) {
        surface.fillRectangle(color.rgba)
    }

}