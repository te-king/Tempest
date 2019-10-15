package engine.world.components

import engine.runtime.*
import engine.world.*

class CameraInput(node: Node) : Component(node), Updatable {

    private val transform = node add Transform::class


    var speed = 8f

    override fun update(delta: Float) {
        if (Input.keyboard[Key.SPACE] == true) {
            transform.translate(transform.up * delta * speed)
        }

        if (Input.keyboard[Key.LEFT_SHIFT] == true) {
            transform.translate(transform.down * delta * speed)
        }

        if (Input.keyboard[Key.A] == true) {
            transform.translate(transform.left * delta * speed)
        }

        if (Input.keyboard[Key.D] == true) {
            transform.translate(transform.right * delta * speed)
        }

        if (Input.keyboard[Key.W] == true) {
            transform.translate(transform.forwards * delta * speed)
        }

        if (Input.keyboard[Key.S] == true) {
            transform.translate(transform.backwards * delta * speed)
        }

        if (Input.keyboard[Key.LEFT] == true) {
            transform.rotate(transform.up, 0.25f * delta * speed)
        }

        if (Input.keyboard[Key.RIGHT] == true) {
            transform.rotate(transform.down, 0.25f * delta * speed)
        }

        if (Input.keyboard[Key.UP] == true) {
            transform.rotate(transform.right, 0.25f * delta * speed)
        }

        if (Input.keyboard[Key.DOWN] == true) {
            transform.rotate(transform.left, 0.25f * delta * speed)
        }

        if (Input.keyboard[Key.Q] == true) {
            transform.rotate(transform.backwards, 0.25f * delta * speed)
        }

        if (Input.keyboard[Key.E] == true) {
            transform.rotate(transform.forwards, 0.25f * delta * speed)
        }
    }

}