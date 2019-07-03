package engine.gui

abstract class Element {


    var x = 0.0f

    var y = 0.0f

    var width = 0.0f

    var height = 0.0f


    abstract fun draw(buffer: Any)


}

