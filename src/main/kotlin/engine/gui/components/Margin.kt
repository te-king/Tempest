package engine.gui.components

import engine.gui.*

class Margin : Frame {

    var left = 4
    var right = 4
    var top = 4
    var bottom = 4


    override var child: Element? = null


    override fun draw(surface: Surface) {
        surface.push(left, top, surface.width - left - right, surface.height - top - bottom)
        child?.draw(surface)
        surface.pop()
    }

}