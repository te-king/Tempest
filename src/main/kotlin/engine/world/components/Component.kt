package engine.world.components

import engine.world.*
import engine.world.controllers.Controller
import kotlin.reflect.KClass

abstract class Component(val node: Node) {

    val scene get() = node.scene


    protected inline fun <reified T : Component> component() = node.add(T::class)
    protected inline fun <reified T : Controller> controller() = scene.add(T::class)

}


