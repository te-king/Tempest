package engine.world

import wrappers.opengl.Device

class Scene (val device: Device) {

    internal val nodeSet = mutableListOf<Node>()

}