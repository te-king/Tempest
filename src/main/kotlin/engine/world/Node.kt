package engine.world

import kotlin.reflect.KClass

class Node (val scene: Scene, var name: String = "New Node") : Iterable<Component> {

    init {
        scene.nodes.add(this)
    }


    val device get() = scene.device

    private val componentMap = hashMapOf<KClass<out Component>, Component>()


    infix fun<T: Component> add(type: KClass<T>) = componentMap.getOrPut(type) { type.constructors.first().call(this) } as T
    infix fun<T: Component> get(type: KClass<T>) = componentMap[type] as T?
    infix fun<T: Component> contains(type: KClass<T>) = componentMap.contains(type)
    infix fun<T: Component> delete(type: KClass<T>) = componentMap.remove(type) as T?


    // Iterable<Component> implementation
    override fun iterator() = componentMap.values.iterator()

}