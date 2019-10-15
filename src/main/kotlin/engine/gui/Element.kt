package engine.gui

import wrappers.nanovg.Surface


abstract class Element {

    abstract fun draw(surface: Surface)

}

