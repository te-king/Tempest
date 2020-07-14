import engine.graphics.StandardShader
import engine.graphics.mesh.TriangleMesh
import engine.graphics.resourceAt
import engine.physics.Container
import engine.physics.Sphere
import engine.runtime.Client
import engine.world.Node
import engine.world.components.*
import engine.world.controllers.Physics
import engine.world.controllers.Renderer
import engine.world.controllers.GraphicsContext
import extensions.find
import math.Color
import math.Float3
import kotlin.random.Random
import kotlin.time.ExperimentalTime


@ExperimentalTime
fun main() {

    val scene = Client.scene
    val sceneWindow = scene.add(GraphicsContext::class)
    val scenePipeline = scene.add(Renderer::class)
    val scenePhysics = scene.add(Physics::class)


    // Load object
    repeat(1) {

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
            Random.nextDouble(-1.0, 1.0).toFloat()
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
        val cameraInput = cameraNode.add(CameraControls::class)
        val cameraRasterizer = cameraNode.add(Camera::class)

        scenePipeline.camera = cameraRasterizer
    }

    // Run the client program
    Client.run()

}