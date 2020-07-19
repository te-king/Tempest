package engine.world.components

import engine.runtime.*
import engine.world.*
import engine.world.controllers.GraphicsContext

class CameraControls(node: Node) : Component(node), Updatable {

    private val window = controller<GraphicsContext>()
    private val transform = component<Transform>()


    var speed = 8f

    override fun update(delta: Double) {

        val movement = delta.toFloat() * speed

        if (window.keyboard[Key.SPACE] == true) transform.translation += transform.up * movement
        if (window.keyboard[Key.LEFT_SHIFT] == true) transform.translation += transform.down * movement

        if (window.keyboard[Key.A] == true) transform.translation += transform.left * movement
        if (window.keyboard[Key.D] == true) transform.translation += transform.right * movement
        if (window.keyboard[Key.W] == true) transform.translation += transform.forwards * movement
        if (window.keyboard[Key.S] == true) transform.translation += transform.backwards * movement

        if (window.keyboard[Key.LEFT] == true) transform.rotate(transform.up, 0.25f * movement)
        if (window.keyboard[Key.RIGHT] == true) transform.rotate(transform.down, 0.25f * movement)
        if (window.keyboard[Key.UP] == true) transform.rotate(transform.right, 0.25f * movement)
        if (window.keyboard[Key.DOWN] == true) transform.rotate(transform.left, 0.25f * movement)

        if (window.keyboard[Key.Q] == true) transform.rotate(transform.backwards, 0.25f * movement)
        if (window.keyboard[Key.E] == true) transform.rotate(transform.forwards, 0.25f * movement)
    }

}