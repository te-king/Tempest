package engine.gui

import wrappers.nanovg.Surface

abstract class Container : Element() {

    val children = mutableListOf<Element>()

}

