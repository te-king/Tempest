package extensions

import engine.world.*
import kotlin.reflect.*


inline val Scene.nestedNodes get() = nodes

inline val Scene.nestedComponents get() = nestedNodes.flatMap(Node::components)


inline fun <reified T : Any> Scene.findAll(type: KClass<T>) = nestedComponents.filterIsInstance<T>() + controllers.filterIsInstance<T>()

inline fun <reified T : Any> Scene.find(type: KClass<T>) = this.findAll(type).firstOrNull()