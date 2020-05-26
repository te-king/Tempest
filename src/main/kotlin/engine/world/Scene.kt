package engine.world

import engine.world.controllers.Controller
import engine.world.controllers.Window
import opengl.Device
import kotlin.reflect.KClass

class Scene {

    //TODO: Make private
    internal val nodeSet = mutableListOf<Node>()

    private val controllerMap = hashMapOf<KClass<out Controller>, Controller>()


    val nodes get() = nodeSet.asSequence()

    val controllers get() = controllerMap.values.asSequence()


    fun<T: Controller> add(type: KClass<T>) = controllerMap.getOrPut(type) { type.constructors.first().call(this) } as T
    fun<T: Controller> get(type: KClass<T>) = controllerMap[type] as T?
    fun<T: Controller> contains(type: KClass<T>) = controllerMap.contains(type)
    fun<T: Controller> delete(type: KClass<T>) = controllerMap.remove(type) as T?

}