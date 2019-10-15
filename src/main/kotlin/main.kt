import engine.gui.*
import engine.runtime.Client
import engine.world.Node
import engine.world.components.*
import math.Colors
import org.lwjgl.opengl.GLUtil
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun main() {

    val scene = Client.scene

    /*
    // Load object
    run {
        val node = resourceAt("""assets/bunny.obj""").loadNode(scene)!!
    }

    // Initialize camera
    run {
        val cameraNode = Node(scene)
        val cameraTransform = cameraNode add Transform::class
        cameraNode add CameraInput::class
        cameraNode add Camera::class
        cameraTransform.translation = Float3(0f, 0f, 5f)
    }

    */

    // Initialize GUI
    run {
        val node = Node(scene)
        val guiRenderer = node add Gui::class

        val container = Dock()
        container.preferredWidth = 86
        container.preferredHeight = 24

        container.child = Button()

        guiRenderer.root = container
    }

    // Run the client program
    Client.run()

}