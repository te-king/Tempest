package engine.world.controllers

import engine.world.Node
import engine.world.Scene
import java.awt.Component
import kotlin.reflect.KClass

abstract class Controller(val scene: Scene) {

    protected inline fun <reified T> components() = scene.nodes.flatMap(Node::components).filterIsInstance<T>()
    protected inline fun <reified T : Controller> controller() = scene.add(T::class)

}
