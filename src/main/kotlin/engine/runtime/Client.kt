package engine.runtime

import engine.world.*
import engine.world.controllers.GraphicsContext
import extensions.*
import kotlin.time.*


object Client {

    // Scene
    var scene = Scene()

    var shouldClose = false


    @ExperimentalTime
    fun run() {

        var mark = TimeSource.Monotonic.markNow()

        while (!shouldClose) {

            for (updatable in scene.findAll<Updatable>())
                updatable.update(mark.elapsedNow().inSeconds)

            mark = TimeSource.Monotonic.markNow()
            Thread.sleep(5)

        }

    }

}