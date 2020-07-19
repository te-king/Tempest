package engine.graphics

import opengl.*

interface Material {

    fun draw(buffer: CommandBuffer, mesh: MeshBuffer)

    fun draw(buffer: CommandBuffer, meshes: Sequence<MeshBuffer>) = meshes.forEach { draw(buffer, it) }

}