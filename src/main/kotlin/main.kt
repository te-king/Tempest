import engine.graphics.resourceAt
import engine.runtime.Client
import engine.world.Camera
import engine.world.DebugCameraController
import engine.world.LightEmitter
import engine.world.Node

fun main() {

    val scene = Client.scene

    // Load object
    for (i in 0 until 1) {
        val node = resourceAt("""assets/obj/testb.obj""").loadNode(scene)!!
    }

    // Initialize camera
    val cameraNode = Node(scene)
    val cameraCamera = cameraNode add Camera::class
    val cameraController = cameraNode add DebugCameraController::class
    cameraCamera.window = Client.window

    val lamp = Node(scene)
    val lampLightEmitter = lamp add LightEmitter::class

    // Run the client program
    Client.run()

}