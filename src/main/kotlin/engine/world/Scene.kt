package engine.world

import wrappers.opengl.Device

class Scene (val device: Device) : Iterable<Node> {

    internal val nodeSet = mutableListOf<Node>()


    // Iterable<Node> implementation
    override fun iterator() = nodeSet.iterator()

}