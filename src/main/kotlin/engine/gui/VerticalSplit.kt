package engine.gui

import wrappers.nanovg.Surface

class VerticalSplit : Container() {

    override fun draw(surface: Surface) {

        if (children.isEmpty()) return

        val childWidth = surface.width
        val childHeight = surface.height / children.size

        children.forEachIndexed { index, element ->
            surface.push(0, index * childHeight, childWidth, childHeight)
            element.draw(surface)
            surface.pop()
        }

    }

}

