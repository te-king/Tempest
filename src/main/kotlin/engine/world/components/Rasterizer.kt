package engine.world.components

import engine.world.Node
import engine.world.Renderable
import engine.world.controllers.Window
import extensions.findAll
import math.ProjectionMatrix
import opengl.CommandBuffer
import opengl.DynamicStorage
import opengl.UniformBuffer
import opengl.setSubData
import kotlin.properties.Delegates

class Rasterizer(node: Node) : Component(node) {

    private val window = controller<Window>()
    private val transform = component<Transform>()

    // TODO: Remove
    private val device = window.device


    val buffer = device.buffer(Int.SIZE_BYTES.toLong() * 16, UniformBuffer, DynamicStorage)


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


    fun CommandBuffer.rasterize() {

        projectionMatrix

        bindUniformBuffer(0, transform.buffer)
        bindUniformBuffer(1, buffer)

        for (renderer in scene.findAll(Renderable::class))
            renderer.draw(this)

    }

}