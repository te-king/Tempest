package engine.graphics

import engine.world.Node
import engine.world.Scene
import engine.world.components.MeshRenderer
import engine.world.components.Transform
import engine.world.controllers.GraphicsContext
import engine.world.controllers.StandardShader
import extensions.toFloat3
import extensions.toQuaternion
import math.Color
import math.Float3
import math.Quaternion
import nanovg.Font
import opengl.*
import org.lwjgl.assimp.*
import org.lwjgl.assimp.Assimp.*
import org.lwjgl.nanovg.NanoVG.nvgCreateFont
import org.lwjgl.stb.STBImage
import java.io.File
import java.io.FileNotFoundException


class Asset(val file: File) {

    init {
        if (!file.exists()) throw FileNotFoundException("External asset not found: $file")
    }

    fun <P : ProgramKind> loadShaderSource(device: Device, type: P) = device.program(file.readText(), type)

    fun loadI8(device: Device, layers: Int = 1, channels: Int = 0): Texture<RGB8, Texture2d>? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture2d(layers, w[0], h[0], RGB8)

            when (c[0]) {
                1 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RED, PixelType.BYTE, it)
                2 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RG, PixelType.BYTE, it)
                3 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGB, PixelType.BYTE, it)
                4 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.BYTE, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadI16(device: Device, layers: Int = 1, channels: Int = 0): Texture<RGB16, Texture2d>? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load_16(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture2d(layers, w[0], h[0], RGB16)

            when (c[0]) {
                1 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RED, PixelType.SHORT, it)
                2 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RG, PixelType.SHORT, it)
                3 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGB, PixelType.SHORT, it)
                4 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.SHORT, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadU8(device: Device, layers: Int = 1, channels: Int = 0): Texture<RGB8, Texture2d>? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture2d(layers, w[0], h[0], RGB8)

            when (c[0]) {
                1 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RED, PixelType.UNSIGNED_BYTE, it)
                2 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RG, PixelType.UNSIGNED_BYTE, it)
                3 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGB, PixelType.UNSIGNED_BYTE, it)
                4 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.UNSIGNED_BYTE, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadU16(device: Device, layers: Int = 1, channels: Int = 0): Texture<RGB16, Texture2d>? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load_16(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture2d(layers, w[0], h[0], RGB16)

            when (c[0]) {
                1 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RED, PixelType.UNSIGNED_SHORT, it)
                2 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RG, PixelType.UNSIGNED_SHORT, it)
                3 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGB, PixelType.UNSIGNED_SHORT, it)
                4 -> result.setSubImage(0, 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.UNSIGNED_SHORT, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadNode(scene: Scene): Node? {

        val device = scene.add(GraphicsContext::class).device


        // Load Scene
        val aiScene = aiImportFile(file.absolutePath, 0) ?: return null

        // Apply post processing
        aiApplyPostProcessing(aiScene, aiProcessPreset_TargetRealtime_Quality)

        val materials = Array<AIMaterial>(aiScene.mNumMaterials()) { AIMaterial.create(aiScene.mMaterials()!!.get(it)) }.map { aiMaterial ->

            val result = scene.add(StandardShader::class).Material()

            // Resolve colors
            val aiDiffuseColor = AIColor4D.create()
            aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_DIFFUSE, 0, 0, aiDiffuseColor)
            result.diffuseColor = aiDiffuseColor.let { Color(it.r(), it.g(), it.b(), it.a()) }


            // Resolve textures
            if (aiGetMaterialTextureCount(aiMaterial, aiTextureType_DIFFUSE) == 1) {

                val path = AIString.create()
                aiGetMaterialTexture(aiMaterial, aiTextureType_DIFFUSE, 0, path, null as IntArray?, null, null, null, null, null)

                Asset(File(file.parentFile, path.dataString())).loadI8(device)?.let {
                    result.diffuseMap = it
                    it.resident = true
                }

            }

            if (aiGetMaterialTextureCount(aiMaterial, aiTextureType_NORMALS) == 1) {

                val path = AIString.create()
                aiGetMaterialTexture(aiMaterial, aiTextureType_NORMALS, 0, path, null as IntArray?, null, null, null, null, null)

                Asset(File(file.parentFile, path.dataString())).loadI8(device)?.let {
                    result.normalMap = it
                    it.resident = true
                }

            }

            result

        }

        val meshMaterialPairs = Array<AIMesh>(aiScene.mNumMeshes()) { AIMesh.create(aiScene.mMeshes()!!.get(it)) }.map { aiMesh ->

            val vertices = aiMesh.mVertices().run {
                val buffer = device.buffer(this, ArrayBuffer, ServerStorage)
                VertexBuffer(buffer, 0, sizeof())
            }

            val normals = aiMesh.mNormals()?.run {
                val buffer = device.buffer(this, ArrayBuffer, ServerStorage)
                VertexBuffer(buffer, 0, this.sizeof())
            }

            val uvs = aiMesh.mTextureCoords(0)?.run {
                val buffer = device.buffer(this, ArrayBuffer, ServerStorage)
                VertexBuffer(buffer, 0, this.sizeof())
            }

            val tangents = aiMesh.mTangents()?.run {
                val buffer = device.buffer(this, ArrayBuffer, ServerStorage)
                VertexBuffer(buffer, 0, this.sizeof())
            }

            val indices = aiMesh.mFaces().flatMap { (0 until it.mNumIndices()).map { i -> it.mIndices().get(i) } }.toIntArray().run {
                val buffer = device.buffer(this, ElementArrayBuffer, ServerStorage)
                ElementBuffer(buffer, size, IndexType.UNSIGNED_INT, PrimitiveType.Triangles)
            }


            val vertexBufferMap = mutableMapOf<Int, VertexBuffer>()

            vertices.let { vertexBufferMap[0] = it }
            normals?.let { vertexBufferMap[1] = it }
            uvs?.let { vertexBufferMap[2] = it }
            tangents?.let { vertexBufferMap[3] = it }

            MeshBuffer(vertexBufferMap, listOf(indices)) to materials[aiMesh.mMaterialIndex()] as Material

        }

        // TODO:
        // Textures??
        // Animations
        // Cameras

        // Lights
        // Other stuff

        // Generate node structure
        fun generateNode(node: AINode): Node = Node(scene).apply {

            name = node.mName().dataString() ?: "New Node"

            // Set transform
            val transform = this.add(Transform::class)

            val scaling = AIVector3D.create()
            val rotation = AIQuaternion.create()
            val position = AIVector3D.create()

            aiDecomposeMatrix(node.mTransformation(), scaling, rotation, position)

            transform.scale = scaling.toFloat3()
            transform.rotation = rotation.toQuaternion()
            transform.translation = position.toFloat3()


            val meshRenderer = this.add(MeshRenderer::class)
            meshRenderer.pairs += Array(node.mNumMeshes()) { meshMaterialPairs[node.mMeshes()!!.get(it)] }

            Array(node.mNumChildren()) { AINode.create(node.mChildren()!!.get(it)) }.forEach { aiChild ->
                val child = generateNode(aiChild)
                child.add(Transform::class).parent = transform
                aiChild.free()
            }

        }

        val result = aiScene.mRootNode()?.run { generateNode(this) }

        aiScene.free()

        return result

    }

//    fun loadFont(name: String): Font? {
//        return nvgCreateFont(nvgContext, name, file.absolutePath).takeUnless { it == -1 }?.let(::Font)
//    }

}

fun assetAt(path: String) = Asset(File(path))
fun resourceAt(path: String) = assetAt(Asset::class.java.classLoader.getResource(path).file)