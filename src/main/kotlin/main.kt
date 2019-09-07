import engine.graphics.assetAt
import engine.graphics.resourceAt
import engine.runtime.Client
import engine.world.Node
import engine.world.components.Camera
import engine.world.components.CameraInput
import engine.world.components.Transform
import math.Float3
import org.lwjgl.opengl.GLUtil
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {

    val scene = Client.scene

    GLUtil.setupDebugMessageCallback()

    // Load object
    run {
        val node = resourceAt("""assets/bunny.obj""").loadNode(scene)!!
    }

    run {
        val node2 = assetAt("""C:\Users\Thomas\Desktop\model\Printable letters.obj""").loadNode(scene)!!
    }

    // Initialize camera
    run {
        val cameraNode = Node(scene)
        val cameraTransform = cameraNode add Transform::class
        val cameraController = cameraNode add CameraInput::class
        val cameraCamera = cameraNode add Camera::class
        cameraTransform.translation = Float3(0f, 0f, 5f)
    }

    // Run the client program
    Client.run()

}