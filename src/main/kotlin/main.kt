import engine.graphics.*
import engine.runtime.*
import engine.world.*
import engine.world.components.*
import engine.world.controllers.RenderPipeline
import kotlin.time.*

@ExperimentalTime
fun main() {

    val scene = Client.scene
    val scenePipeline = scene.add(RenderPipeline::class)


    // Load object
    run {
        val node = resourceAt("""assets/bunny.obj""").loadNode(scene) ?: return@run
    }

    // Initialize camera
    run {
        val cameraNode = Node(scene, "Camera")
        val cameraTransform = cameraNode add Transform::class
        val cameraInput = cameraNode add CameraInput::class
        val cameraCam = cameraNode add Rasterizer::class

        scenePipeline.primaryRasterizer = cameraCam
    }

    //    run {
    //        val node = Node(scene)
    //        val guiRenderer = node add Gui::class
    //        guiRenderer.output = Client.framebuffer
    //
    //        val container = Dock()
    //        container.preferredWidth = 86
    //        container.preferredHeight = 24
    //        container.child = Button()
    //
    //        guiRenderer.root = container
    //    }


    // Initialize GUI
//    run {
//        val node = Node(scene, "Gooi")
//        val guiRenderer = node add Gui::class
//        guiRenderer.output = Client.framebuffer
//
//        guiRenderer.root = List(scene.nodeSet)
//    }

    // Run the client program
    Client.run()

}