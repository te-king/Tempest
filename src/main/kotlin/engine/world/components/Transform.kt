package engine.world.components

import engine.world.*
import engine.world.controllers.GraphicsContext
import extensions.sizeOf
import math.*
import opengl.*
import kotlin.properties.*


class Transform(node: Node) : Component(node) {

    private val window = controller<GraphicsContext>()


    var parent: Transform? = null
        set(value) {
            field?.childrenSet?.remove(this)
            field = value
            field?.childrenSet?.add(this)
        }

    private val childrenSet = mutableSetOf<Transform>()

    val children get() = childrenSet.asSequence()


    // Buffer
    val buffer = window.device.buffer(sizeOf(Float4x4::class, Float4x4::class), UniformBuffer, DynamicStorage)


    // Transforms
    var translation by Delegates.observable(Float3.zero) { _, _, _ -> invalidateLocal() }
    var rotation by Delegates.observable(Quaternion.identity) { _, _, _ -> invalidateLocal() }
    var scale by Delegates.observable(Float3.one) { _, _, _ -> invalidateLocal() }


    fun rotate(q: Quaternion) {
        rotation = q * rotation
    }

    fun rotate(axis: Float3, angle: Float) {
        rotate(Quaternion.fromAxisAngle(axis, angle))
    }


    // Directions
    val backwards get() = worldMatrix.matrix.column(2).xyz.normalized
    val forwards get() = -backwards

    val right get() = worldMatrix.matrix.column(0).xyz.normalized
    val left get() = -right

    val up get() = worldMatrix.matrix.column(1).xyz.normalized
    val down get() = -up


    // Local Matrix
    private var localInvalid = true

    var localMatrix = TransformationMatrix.identity
        get() {
            if (localInvalid) validateLocal()
            return field
        }
        private set(value) {
            buffer.setSubData(0, value.matrix.toFloatArray())
            field = value
        }

    private fun invalidateLocal() {
        localInvalid = true
        invalidateWorld()
    }

    private fun validateLocal() {
        localMatrix =
            TransformationMatrix.translation(translation) *
                    TransformationMatrix.rotation(rotation) *
                    TransformationMatrix.scaling(scale)
        localInvalid = false
    }


    // World Matrix
    private var worldInvalid = true

    var worldMatrix = TransformationMatrix.identity
        get() {
            if (worldInvalid) validateWorld()
            return field
        }
        private set(value) {
            buffer.setSubData(Int.SIZE_BYTES.toLong() * 16, value.matrix.toFloatArray())
            field = value
        }

    private fun invalidateWorld() {
        worldInvalid = true
        for (it in childrenSet) it.invalidateWorld()
    }

    private fun validateWorld() {
        worldMatrix = (parent?.worldMatrix ?: TransformationMatrix.identity) * localMatrix
        worldInvalid = false
    }


    override fun toString() = "Position: $translation, Scale: $scale, Rotation: $rotation"

}