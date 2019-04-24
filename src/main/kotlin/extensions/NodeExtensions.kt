package extensions

import engine.world.Node
import engine.world.Transform
import kotlin.reflect.KClass


val Node.nestedNodes get() = (this get Transform::class)?.children?.map(Transform::node) ?: sequenceOf()

val Node.nestedComponents get() = nestedNodes.flatMap(Node::components)


infix fun<T: Any> Node.findAll(type: KClass<T>) = nestedComponents.filterIsInstance(type.java)

infix fun<T: Any> Node.find(type: KClass<T>) = (this findAll type).firstOrNull()