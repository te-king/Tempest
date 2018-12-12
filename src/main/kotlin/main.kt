import engine.graphics.resourceAt
import engine.runtime.Client
import engine.world.*
import org.lwjgl.opengl.GLUtil

fun main(args: Array<String>) {

    val scene = Client.scene

    val v = GLUtil.setupDebugMessageCallback()

    // Load object
    for (i in 0 until 1) {
        val node = resourceAt("""assets/cube.obj""").loadNode(scene)!!
        node add RotationScript::class
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