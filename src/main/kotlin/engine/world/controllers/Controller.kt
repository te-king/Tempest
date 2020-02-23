package engine.world.controllers

import engine.world.Scene

abstract class Controller(val scene: Scene) {

    val device get() = scene.device

}