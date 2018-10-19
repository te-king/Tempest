package engine.world

import wrappers.opengl.Device

class Scene (val device: Device) : Iterable<Node> {

    internal val nodes = mutableListOf<Node>()


    // Iterable<Node> implementation
    override fun iterator() = nodes.iterator()

}