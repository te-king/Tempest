package engine.world.components

import engine.world.Node
import engine.world.controllers.GraphicsContext
import math.ProjectionMatrix
import opengl.CommandBuffer
import opengl.DynamicStorage
import opengl.UniformBuffer
import opengl.setSubData
import kotlin.properties.Delegates

class Camera(node: Node) : Component(node) {

    private val window = controller<GraphicsContext>()
    private val transform = component<Transform>()


    val buffer = window.device.buffer(Int.SIZE_BYTES.toLong() * 16, UniformBuffer, DynamicStorage)


    var fieldOfView by Delegates.observable(0.7854f) { _, _, _ -> projectionInvalid = true }
    var aspectRatio by Delegates.observable(640f / 480f) { _, _, _ -> projectionInvalid = true }
    var nearPlaneClipping by Delegates.observable(0.05f) { _, _, _ -> projectionInvalid = true }
    var farPlaneClipping by Delegates.observable(500f) { _, _, _ -> projectionInvalid = true }


    // Projection Matrix
    private var projectionInvalid = true

    var projectionMatrix = ProjectionMatrix.identity
        get() {
            if (projectionInvalid) {
                projectionMatrix = ProjectionMatrix.perspectiveFieldOfView(fieldOfView, aspectRatio, nearPlaneClipping, farPlaneClipping)
                projectionInvalid = false
            }
            return field
        }
        private set(value) {
            buffer.setSubData(0, value.matrix.toFloatArray())
            field = value
        }


    fun attach(commandBuffer: CommandBuffer) {

        projectionMatrix

        commandBuffer.bindUniformBuffer(0, transform.buffer)
        commandBuffer.bindUniformBuffer(1, buffer)

    }

}