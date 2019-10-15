package engine.gui

import wrappers.nanovg.Surface

class HorizontalSplit : Container() {

    override fun draw(surface: Surface) {

        if (children.isEmpty()) return

        val childWidth = surface.width / children.size
        val childHeight = surface.height

        children.forEachIndexed { index, element ->
            surface.push(index * childWidth, 0, childWidth, childHeight)
            element.draw(surface)
            surface.pop()
        }

    }

}