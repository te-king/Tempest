package engine.gui.components

import engine.gui.*

class List<T>(val items: Iterable<T>) : Element {

    var elementHeight = 48
    var elementWidth = 256

    var generator: (T) -> Element = {

        Label(it.toString())

    }

    override fun draw(surface: Surface) {

        items.forEachIndexed { index: Int, item: T ->

            val offset = index * elementHeight

            if (offset >= surface.height) return

            surface.push(0, offset, elementWidth, elementHeight)
            generator(item).draw(surface)
            surface.pop()

        }

    }

}