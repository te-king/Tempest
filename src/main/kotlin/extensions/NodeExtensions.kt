package extensions

import engine.world.Component
import engine.world.Node
import engine.world.Transform
import kotlin.reflect.KClass

infix fun<T: Component> Node.findExact(type: KClass<T>): T? {
    val transform = this get Transform::class ?: return null
    for (child in transform) return child.node.get(type) ?: continue
    for (child in transform) return child.node.findExact(type) ?: continue
    return null
}

infix fun<T: Component> Node.find(type: KClass<T>): T? {
    val transform = this get Transform::class ?: return null
    for (child in transform) return child.node.filterIsInstance(type.java).firstOrNull() ?: continue
    for (child in transform) return child.node.find(type) ?: continue
    return null
}


infix fun<T: Component> Node.findAllExact(type: KClass<T>): Sequence<T> = sequence {
    val transform = this@findAllExact get Transform::class ?: return@sequence
    for (child in transform) child.node.get(type)?.let { yield(it) }
    for (child in transform) yieldAll(child.node.findAllExact(type))
}

infix fun<T: Component> Node.findAll(type: KClass<T>): Sequence<T> = sequence {
    val transform = this@findAll get Transform::class ?: return@sequence
    for (child in transform) yieldAll(child.node.filterIsInstance(type.java))
    for (child in transform) yieldAll(child.node.findAll(type))
}