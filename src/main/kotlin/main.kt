import engine.graphics.*
import engine.runtime.*
import engine.world.*
import engine.world.components.*
import engine.world.controllers.RenderPipeline
import math.Float3
import math.Quaternion
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
        val cameraInput = cameraNode add CameraInput::class
        val cameraRasterizer = cameraNode add Rasterizer::class

        scenePipeline.primaryRasterizer = cameraRasterizer
    }

    // Initialize primary light source
    run {
        val lightNode = Node(scene, "Light")
        val lightTransform = lightNode add Transform::class
        val lightRasterizer = lightNode add Rasterizer::class

        lightTransform.translation = Float3(0f, 2f, 0f)
        lightTransform.rotation = Quaternion.fromAxisAngle(Float3.left, 90f * 0.0174533f)
        scenePipeline.primaryShadowCaster = lightRasterizer
    }

    // Run the client program
    Client.run()

}