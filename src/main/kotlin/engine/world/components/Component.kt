package engine.world.components

import engine.world.*

abstract class Component(val node: Node) {

    val device get() = node.device

    val scene get() = node.scene

}


