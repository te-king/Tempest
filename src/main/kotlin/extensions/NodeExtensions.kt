package extensions

import engine.world.Node
import engine.world.components.Transform
import kotlin.reflect.KClass


inline val Node.nestedNodes get() = (this get Transform::class)?.children?.map(Transform::node) ?: sequenceOf()

inline val Node.nestedComponents get() = nestedNodes.flatMap(Node::components)


inline infix fun<reified T: Any> Node.findAll(type: KClass<T>) = nestedComponents.filterIsInstance<T>()

inline infix fun<reified T: Any> Node.find(type: KClass<T>) = (this findAll type).firstOrNull()