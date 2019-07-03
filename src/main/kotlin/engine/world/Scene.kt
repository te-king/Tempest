package engine.world

import engine.world.components.Component
import engine.world.controllers.Controller
import wrappers.opengl.Device
import kotlin.reflect.KClass

class Scene (val device: Device) {

    //TODO: Make private
    internal val nodeSet = mutableListOf<Node>()

    private val controllerMap = hashMapOf<KClass<out Controller>, Controller>()


    val nodes get() = nodeSet.asSequence()

    val controllers get() = controllerMap.values.asSequence()


    infix fun<T: Controller> add(type: KClass<T>) = controllerMap.getOrPut(type) { type.constructors.first().call(this) } as T
    infix fun<T: Controller> get(type: KClass<T>) = controllerMap[type] as T?
    infix fun<T: Controller> contains(type: KClass<T>) = controllerMap.contains(type)
    infix fun<T: Controller> delete(type: KClass<T>) = controllerMap.remove(type) as T?

}