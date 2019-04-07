import engine.graphics.RaymarchShader
import engine.graphics.resourceAt
import engine.runtime.Client
import engine.world.*
import extensions.find
import math.Float3

fun main() {

    val scene = Client.scene

    // Load object
    run {
        val node = resourceAt("""assets/bunny.obj""").loadNode(scene)!!
        //val nodeRotator = node add RotationScript::class
        val nodeRenderer = node find MeshRenderer::class

        nodeRenderer?.let {
            it.pairs[0] = it.pairs[0].first to Client.raymarchShader.Material()
        }

        val nodeTransform = node add Transform::class
        nodeTransform.scale = Float3(100f, 100f, 100f)

    }

    // Initialize camera
    val cameraNode = Node(scene)
    val cameraCamera = cameraNode add Camera::class
    val cameraController = cameraNode add DebugCameraController::class
    val cameraTransform = cameraNode add Transform::class
    cameraCamera.window = Client.window
    cameraTransform.translation = Float3(0f, 0f, 10f)

    val lamp = Node(scene)
    val lampLightEmitter = lamp add LightEmitter::class

    // Run the client program
    Client.run()

}