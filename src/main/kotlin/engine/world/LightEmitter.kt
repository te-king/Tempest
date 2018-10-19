package engine.world

import wrappers.opengl.RenderCommandBuffer

class LightEmitter(node: Node) : Component(node) {

    private val transform = node add Transform::class


    //var type: Light = Light.Ambient(Float3(1f, 0f, 0f), 1f)


    fun draw(buffer: RenderCommandBuffer) {

        buffer.bindUniformBuffer(2, transform.buffer)
        //type.draw(buffer)

    }

}