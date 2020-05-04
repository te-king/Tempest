import engine.graphics.*
import engine.physics.*
import engine.runtime.*
import engine.world.*
import engine.world.components.*
import engine.world.controllers.*
import extensions.*
import math.*
import kotlin.random.*
import kotlin.time.*

@ExperimentalTime
fun main() {

    val scene = Client.scene
    val scenePipeline = scene.add(Renderer::class)
    val scenePhysics = scene.add(Physics::class)


    // Load object
    repeat(100) {

        val node = resourceAt("""assets/bunny.obj""").loadNode(scene) ?: return
        val nodeTransform = node.add(Transform::class)
        val nodePhysicsBody = node.add(PhysicsBody::class)

        val nodeMeshRenderer = node.find(MeshRenderer::class) ?: return

        for (mat in nodeMeshRenderer.pairs.mapNotNull { it.second }.filterIsInstance<StandardShader.Material>()) {

            mat.diffuseColor = Color(
                Random.nextDouble(.0, 1.0).toFloat(),
                Random.nextDouble(.0, 1.0).toFloat(),
                Random.nextDouble(.0, 1.0).toFloat(),
                1f
            )

        }

        nodeTransform.translation = Float3(
            Random.nextDouble(-1.0, 1.0).toFloat(),
            Random.nextDouble(1.0, 2.0).toFloat(),
            Random.nextDouble(-1.0, 1.0).toFloat(),
        )
        nodePhysicsBody.collider = Sphere(0.1f)

    }

    // Load Surface
    run {
        val node = Node(scene)
        val nodePhysicsBody = node.add(PhysicsBody::class)

        nodePhysicsBody.collider = Container
        nodePhysicsBody.static = true
    }


    // Initialize camera
    run {
        val cameraNode = Node(scene, "Camera")
        val cameraInput = cameraNode.add(CameraInput::class)
        val cameraRasterizer = cameraNode.add(Rasterizer::class)

        scenePipeline.primaryRasterizer = cameraRasterizer
    }

    // Initialize primary light source
    run {
        val lightNode = Node(scene, "Light")
        val lightTransform = lightNode.add(Transform::class)
        val lightRasterizer = lightNode.add(Rasterizer::class)

        lightTransform.translation = Float3(0f, 2f, 0f)
        lightTransform.rotation = Quaternion.fromAxisAngle(Float3.left, 90f * 0.0174533f)
        scenePipeline.primaryShadowCaster = lightRasterizer
    }

    // Run the client program
    Client.run()

}