package extensions

import engine.world.Node
import engine.world.components.Transform


inline val Node.nestedNodes get() = this.get(Transform::class)?.children?.map(Transform::node) ?: sequenceOf()

inline val Node.nestedComponents get() = nestedNodes.flatMap(Node::components)


inline fun <reified T : Any> Node.findAll() = nestedComponents.filterIsInstance<T>()

inline fun <reified T : Any> Node.find() = findAll<T>().firstOrNull()