package extensions

import engine.world.Component
import engine.world.Scene
import kotlin.reflect.KClass

val Scene.nestedNodes get() = nodeSet.asSequence()

val Scene.nestedComponents get() = nestedNodes.flatMap { it.components }


infix fun<T: Component> Scene.findAll(type: KClass<T>) = nestedComponents.filterIsInstance(type.java)

infix fun<T: Component> Scene.find(type: KClass<T>) = (this findAll type).firstOrNull()