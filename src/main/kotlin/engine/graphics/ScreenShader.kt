package engine.graphics

import engine.runtime.Client
import wrappers.opengl.Program
import wrappers.opengl.ProgramType
import java.io.File

abstract class ScreenShader (val fragmentProgram: Program) {

    companion object {
        private val objectVertexProgram = resourceAt("""shaders/ScreenVertexShader.glsl""").loadShaderSource(Client.device, ProgramType.VERTEX)
        // Client.device.program(ProgramType.VERTEX, File("""shaders/ScreenVertexShader.glsl""").readText())
    }


    val pipeline = Client.device.pipeline().apply {
        setStage(ProgramType.VERTEX, objectVertexProgram)
        setStage(ProgramType.FRAGMENT, fragmentProgram)
    }

}