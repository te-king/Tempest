package engine.world

import kotlin.reflect.KClass

abstract class Component (val node: Node) {

    val device get() = node.device

    val scene get() = node.scene

}


