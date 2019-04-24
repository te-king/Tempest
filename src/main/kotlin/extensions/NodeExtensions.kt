package extensions

import engine.world.Component
import engine.world.Node
import engine.world.Transform
import kotlin.reflect.KClass

val Node.components get() = componentMap.values.asSequence()


val Node.nestedNodes get() = (this get Transform::class)?.children?.map(Transform::node) ?: sequenceOf()

val Node.nestedComponents get() = nestedNodes.flatMap(Node::components)


infix fun<T: Component> Node.findAll(type: KClass<T>) = nestedComponents.filterIsInstance(type.java)

infix fun<T: Component> Node.find(type: KClass<T>) = (this findAll type).firstOrNull()