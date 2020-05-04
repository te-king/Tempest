package extensions

import engine.world.*
import engine.world.components.*
import kotlin.reflect.*


inline val Node.nestedNodes get() = this.get(Transform::class)?.children?.map(Transform::node) ?: sequenceOf()

inline val Node.nestedComponents get() = nestedNodes.flatMap(Node::components)


inline infix fun <reified T : Any> Node.findAll(type: KClass<T>) = nestedComponents.filterIsInstance<T>()

inline infix fun <reified T : Any> Node.find(type: KClass<T>) = (this findAll type).firstOrNull()