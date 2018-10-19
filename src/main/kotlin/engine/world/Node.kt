package engine.world

import kotlin.reflect.KClass

class Node (val scene: Scene, var name: String = "New Node") : Iterable<Component> {

    init {
        scene.nodes.add(this)
    }


    val device get() = scene.device

    private val componentMap = hashMapOf<KClass<*>, Component>()


    infix fun<T: Component> add(type: KClass<T>) = componentMap.getOrPut(type) { type.constructors.first().call(this) } as T
    infix fun<T: Component> get(type: KClass<T>) = componentMap[type] as? T
    infix fun<T: Component> contains(type: KClass<T>) = componentMap.contains(type)
    infix fun<T: Component> delete(type: KClass<T>) = componentMap.remove(type) as? T

    fun<T: Component> addComponent(type: Class<T>) = this add type.kotlin
    fun<T: Component> getComponent(type: Class<T>) = this get type.kotlin
    fun<T: Component> containsComponent(type: Class<T>) = this contains type.kotlin
    fun<T: Component> deleteComponent(type: Class<T>) = this delete type.kotlin


    // Iterable<Component> implementation
    override fun iterator() = componentMap.values.iterator()

}