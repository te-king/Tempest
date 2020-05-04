package engine.world

import engine.world.components.Component
import kotlin.reflect.KClass

class Node(val scene: Scene, var name: String = "New Node") {

    init {
        scene.nodeSet.add(this)
    }

    val device get() = scene.device


    private val componentMap = hashMapOf<KClass<out Component>, Component>()

    val components get() = componentMap.values.asSequence()


    fun <T : Component> add(type: KClass<T>) = componentMap.getOrPut(type) { type.constructors.first().call(this) } as T
    fun <T : Component> get(type: KClass<T>) = componentMap[type] as T?
    fun <T : Component> contains(type: KClass<T>) = componentMap.contains(type)
    fun <T : Component> delete(type: KClass<T>) = componentMap.remove(type) as T?


    override fun toString() = "Node(name='$name')"

}