import engine.graphics.resourceAt
import engine.runtime.Client
import engine.world.*
import engine.world.components.*
import math.Float3
import math.Quaternion
import kotlin.reflect.KClass

fun main() {

    val scene = Client.scene

    val worldNode = Node(scene, "World")
    //val worldController = worldNode add PhysicsController::class
    //worldController.gravity = Float3.zero

    // Load object
    run {
        val node = resourceAt("""assets/bunny.obj""").loadNode(scene)!!
        val nodeTransform = node add Transform::class
        val nodePhysics = node add PhysicsBody::class

        nodeTransform.parent = worldNode add Transform::class
        nodePhysics.rotationDelta = Quaternion.fromAxisAngle(Float3.up, 1f) * Quaternion.fromAxisAngle(Float3.right, 0.912f)
        nodePhysics.translationDelta = Float3(0f, 1f, 0f)
    }

    // Initialize camera
    val cameraNode = Node(scene)
    val cameraTransform = cameraNode add Transform::class
    val cameraController = cameraNode add CameraInput::class
    val cameraCamera = cameraNode add Camera::class
    cameraCamera.window = Client.window
    cameraTransform.translation = Float3(0f, 0f, 5f)

    // Run the client program
    Client.run()

}