package engine.runtime

import engine.world.*
import engine.world.controllers.Window
import extensions.*
import kotlin.time.*

object Client {

    // Scene
    var scene = Scene()

    var shouldClose = false


    // TODO: Remove
    @Deprecated("Use 'scene.add(Window::class)' to add a windowing system and opengl context")
    val device = scene.add(Window::class).device


    @ExperimentalTime
    fun run() {

        var mark = MonoClock.markNow()

        while (!shouldClose) {

            for (updatable in scene.findAll(Updatable::class))
                updatable.update(mark.elapsedNow().inSeconds.toFloat())

            mark = MonoClock.markNow()
            Thread.sleep(1)

        }

    }

}