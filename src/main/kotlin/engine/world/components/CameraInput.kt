package engine.world.components

import engine.runtime.Input
import engine.runtime.Key
import engine.world.Node
import engine.world.Updatable

class CameraInput(node: Node) : Component(node), Updatable {

    private val transform = node add Transform::class


    var speed = 4f

    override fun update(delta: Float) {
        if (Input.keys[Key.SPACE] == true) {
            transform.translate(transform.up * delta * speed)
        }

        if (Input.keys[Key.LEFT_SHIFT] == true) {
            transform.translate(transform.down * delta * speed)
        }

        if (Input.keys[Key.A] == true) {
            transform.translate(transform.left * delta * speed)
        }

        if (Input.keys[Key.D] == true) {
            transform.translate(transform.right * delta * speed)
        }

        if (Input.keys[Key.W] == true) {
            transform.translate(transform.forwards * delta * speed)
        }

        if (Input.keys[Key.S] == true) {
            transform.translate(transform.backwards * delta * speed)
        }

        if (Input.keys[Key.LEFT] == true) {
            transform.rotate(transform.up, 0.25f * delta * speed)
        }

        if (Input.keys[Key.RIGHT] == true) {
            transform.rotate(transform.down, 0.25f * delta * speed)
        }

        if (Input.keys[Key.UP] == true) {
            transform.rotate(transform.right, 0.25f * delta * speed)
        }

        if (Input.keys[Key.DOWN] == true) {
            transform.rotate(transform.left, 0.25f * delta * speed)
        }

        if (Input.keys[Key.Q] == true) {
            transform.rotate(transform.backwards, 0.25f * delta * speed)
        }

        if (Input.keys[Key.E] == true) {
            transform.rotate(transform.forwards, 0.25f * delta * speed)
        }
    }

}