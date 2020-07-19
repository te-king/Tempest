package extensions

import engine.world.*
import kotlin.reflect.*


inline val Scene.nestedNodes get() = nodes

inline val Scene.nestedComponents get() = nestedNodes.flatMap(Node::components)


inline fun <reified T : Any> Scene.findAll() = nestedComponents.filterIsInstance<T>() + controllers.filterIsInstance<T>()

inline fun <reified T : Any> Scene.find() = findAll<T>().firstOrNull()