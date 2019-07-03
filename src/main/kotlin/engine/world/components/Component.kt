package engine.world.components

import engine.world.Node

abstract class Component(val node: Node) {

    val device get() = node.device

    val scene get() = node.scene

}


