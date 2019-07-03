package engine.world.controllers

import engine.world.Node

abstract class Controller(val node: Node) {

    val device get() = node.device

    val scene get() = node.scene

}