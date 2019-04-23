import engine.graphics.RaymarchShader
import engine.graphics.resourceAt
import engine.runtime.Client
import engine.world.*
import extensions.find
import math.Float3
import math.Quaternion

fun main() {

    val scene = Client.scene

    // Load object
    run {
        val node = resourceAt("""assets/bunny.obj""").loadNode(scene)!!
        val nodePhysics = node add RigidBody::class
        nodePhysics.rotationDelta = Quaternion.fromAxisAngle(Float3.up, 1f)
    }

    // Initialize camera
    val cameraNode = Node(scene)
    val cameraCamera = cameraNode add Camera::class
    val cameraController = cameraNode add DebugCameraController::class
    val cameraTransform = cameraNode add Transform::class
    cameraCamera.window = Client.window
    cameraTransform.translation = Float3(0f, 0f, 5f)

    val lamp = Node(scene)
    val lampLightEmitter = lamp add LightEmitter::class

    // Run the client program
    Client.run()

}