package engine.world

// TODO: Find Memory Leak

import math.*
import wrappers.opengl.BufferUsage

class Transform (node: Node) : Component(node), Updatable {

    var parent: Transform? = null
        set(value) {
            field?.childrenSet?.remove(this)
            field = value
            field?.childrenSet?.add(this)
        }

    internal val childrenSet = mutableSetOf<Transform>()

    val children get() = childrenSet.asSequence()


    // Buffer
    val buffer = device.buffer(Int.SIZE_BYTES.toLong() * 16 * 2, BufferUsage.DYNAMIC)


    // Transforms
    var translation = Float3.zero
        set(value) {
            invalidateLocal()
            field = value
        }


    var rotation = Quaternion.identity
        set(value) {
            invalidateLocal()
            field = value
        }

    var scale = Float3.one
        set(value) {
            invalidateLocal()
            field = value
        }


    fun translate(v: Float3) {
        translation += v
    }

    fun rotate(q: Quaternion) {
        rotation = q * rotation
    }

    fun rotate(axis: Float3, angle: Float) {
        rotate(Quaternion.fromAxisAngle(axis, angle))
    }

    fun scale(v: Float3) {
        scale += v
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



    // WorldConsumer Matrix
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
        childrenSet.forEach { it.invalidateWorld() }
    }

    private fun validateWorld() {
        worldMatrix = (parent?.worldMatrix ?: TransformationMatrix.identity) * localMatrix
        worldInvalid = false
    }


    // TODO("Temporary workaround, wont be needed with mvp generation on CPU")
    override fun update(delta: Float) {
        if (worldInvalid) validateWorld()
    }


    override fun toString() = """Position: $translation, Scale: $scale, Rotation: $rotation"""

}