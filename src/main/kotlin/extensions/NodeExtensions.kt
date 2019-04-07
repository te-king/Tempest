package extensions

import engine.world.Component
import engine.world.Node
import engine.world.Transform
import kotlin.reflect.KClass

infix fun<T: Component> Node.find(type: KClass<T>): T? {
    val transform = this get Transform::class ?: return null

    for (child in transform.childeren) return child.node get type ?: continue
    for (child in transform.childeren) return child.node find type ?: continue

    return null
}