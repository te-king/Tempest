package engine.graphics

import engine.runtime.Client
import engine.world.*
import math.Float3
import math.Quaternion
import org.lwjgl.assimp.*
import org.lwjgl.assimp.Assimp.*
import org.lwjgl.opengl.GL11.GL_FLOAT
import org.lwjgl.stb.STBImage
import wrappers.opengl.*
import java.io.File
import java.io.FileNotFoundException


class Asset (val file: File) {

    init {
        if (!file.exists()) throw FileNotFoundException("External asset not found.")
    }

    fun loadShaderSource(device: Device, type: ProgramType) = device.program(type, file.readText())

    fun loadI8(device: Device, layers: Int = 1, channels: Int = 0): Texture? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture(TextureTarget.TEXTURE_2D)
            result.allocate2d(layers, TextureFormat.RGB8, w[0], h[0])

            when (c[0]) {
                1 -> result[0].setSubImage2d(0, 0, w[0], h[0], PixelLayout.RED, PixelType.BYTE, it)
                2 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RG, PixelType.BYTE, it)
                3 -> result[0].setSubImage2d(0, 0, w[0], h[0], PixelLayout.RGB, PixelType.BYTE, it)
                4 -> result[0].setSubImage2d(0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.BYTE, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadI16(device: Device, layers: Int = 1, channels: Int = 0): Texture? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load_16(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture(TextureTarget.TEXTURE_2D)
            result.allocate2d(layers, TextureFormat.RGB16, w[0], h[0])

            when (c[0]) {
                1 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RED, PixelType.SHORT, it)
                2 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RG, PixelType.SHORT, it)
                3 -> result[0].setSubImage2d(0, 0, w[0], h[0], PixelLayout.RGB, PixelType.SHORT, it)
                4 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.SHORT, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadU8(device: Device, layers: Int = 1, channels: Int = 0): Texture? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture(TextureTarget.TEXTURE_2D)
            result.allocate2d(layers, TextureFormat.RGB8, w[0], h[0])

            when (c[0]) {
                1 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RED, PixelType.UNSIGNED_BYTE, it)
                2 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RG, PixelType.UNSIGNED_BYTE, it)
                3 -> result[0].setSubImage2d(0, 0, w[0], h[0], PixelLayout.RGB, PixelType.UNSIGNED_BYTE, it)
                4 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.UNSIGNED_BYTE, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadU16(device: Device, layers: Int = 1, channels: Int = 0): Texture? {
        val w = intArrayOf(0)
        val h = intArrayOf(0)
        val c = intArrayOf(0)

        STBImage.stbi_load_16(file.absolutePath, w, h, c, channels)?.let {
            val result = device.texture(TextureTarget.TEXTURE_2D)
            result.allocate2d(layers, TextureFormat.RGB16, w[0], h[0])

            when (c[0]) {
                1 -> result[0].setSubImage2d(0, 0, w[0], h[0], PixelLayout.RED, PixelType.UNSIGNED_SHORT, it)
                2 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RG, PixelType.UNSIGNED_SHORT, it)
                3 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RGB, PixelType.UNSIGNED_SHORT, it)
                4 -> result[0].setSubImage2d( 0, 0, w[0], h[0], PixelLayout.RGBA, PixelType.UNSIGNED_SHORT, it)
            }

            STBImage.stbi_image_free(it)
            return result
        }

        return null
    }

    fun loadNode(scene: Scene): Node? {

        // Load Scene
        val aiScene = aiImportFile(file.absolutePath, 0) ?: return null

        // Apply post processing
        aiApplyPostProcessing(aiScene, aiProcessPreset_TargetRealtime_Quality)


        val materials = Array<AIMaterial>(aiScene.mNumMaterials()) { AIMaterial.create(aiScene.mMaterials()!!.get(it)) }.map { aiMaterial: AIMaterial ->

            val result = Client.standardObjectShader.Material()

            // TODO: Fix texture loading
            resourceAt("""assets/diffuse.jpg""").loadI8(scene.device)?.let {
                result.diffuseMap = it
                it.resident = true
            }

            resourceAt( """assets/normal.png""").loadU16(scene.device)?.let {
                result.normalMap = it
                it.resident = true
            }

            result

        }

        val meshMaterialPairs = Array<AIMesh>(aiScene.mNumMeshes()) { AIMesh.create(aiScene.mMeshes()!!.get(it)) }.map { aiMesh: AIMesh ->

            val mesh = Mesh(scene.device)

            // Vertices
            mesh.vertexBuffers[0] = aiMesh.mVertices().run {
                val buffer = scene.device.buffer()//.apply { allocateImmutable(sizeof() * remaining().toLong(), Pointer<Unit>(address()), 0) }
                buffer.allocateImmutable(this, 0)

                mesh[0].apply {
                    bufferIndex = 0
                    format(3, GL_FLOAT)
                    enabled = true
                }

                Mesh.VertexBuffer(buffer, 0, sizeof())
            }

            // Normals
            mesh.vertexBuffers[1] = aiMesh.mNormals()?.run {
                val buffer = scene.device.buffer()//.apply { allocateImmutable(sizeof() * remaining().toLong(), Pointer<Unit>(address()), 0) }
                buffer.allocateImmutable(this, 0)

                mesh[1].apply {
                    bufferIndex = 1
                    format(3, GL_FLOAT)
                    enabled = true
                }

                Mesh.VertexBuffer(buffer, 0, this.sizeof())
            }

            // Uvs
            mesh.vertexBuffers[2] = aiMesh.mTextureCoords(0)?.run {
                val buffer = scene.device.buffer()//.apply { allocateImmutable(sizeof() * remaining().toLong(), Pointer<Unit>(address()), 0) }
                buffer.allocateImmutable(this, 0)

                mesh[2].apply {
                    bufferIndex = 2
                    format(3, GL_FLOAT)
                    enabled = true
                }

                Mesh.VertexBuffer(buffer, 0, this.sizeof())
            }

            // Tangents
            mesh.vertexBuffers[3] = aiMesh.mTangents()?.run {
                val buffer = scene.device.buffer()//.apply { allocateImmutable(sizeof() * remaining().toLong(), Pointer<Unit>(address()), 0) }
                buffer.allocateImmutable(this, 0)

                mesh[3].apply {
                    bufferIndex = 3
                    format(3, GL_FLOAT)
                    enabled = true
                }

                Mesh.VertexBuffer(buffer, 0, this.sizeof())
            }

            val indices = aiMesh.mFaces().flatMap {  (0 until it.mNumIndices()).map { i -> it.mIndices().get(i) } }.toIntArray()

            mesh.indexBuffer = scene.device.buffer().apply { allocateImmutable(indices, 0) }
            mesh.indexCount = aiMesh.mNumFaces() * aiMesh.mFaces().mNumIndices()
            mesh.indexType = IndexType.UNSIGNED_INT
            mesh.primitiveType = PrimitiveType.TRIANGLES

            mesh to materials[aiMesh.mMaterialIndex()]

        }

        // TODO:
        // Textures
        // Animations
        // Cameras

        // Lights
        // Other stuff

        // Generate node structure
        fun generateNode(node: AINode): Node = Node(scene).apply {

            name = node.mName().dataString() ?: "New Node"

            // Set transform
            val transform = this add Transform::class

            val scaling = AIVector3D.create()
            val rotation = AIQuaternion.create()
            val position = AIVector3D.create()

            aiDecomposeMatrix(node.mTransformation(), scaling, rotation, position)

            transform.scale = Float3(scaling.x(), scaling.y(), scaling.z())
            transform.rotation = Quaternion(rotation.x(), rotation.y(), rotation.z(), rotation.w())
            transform.translation = Float3(position.x(), position.y(), position.z())

            if (node.mNumMeshes() == 1) {
                val meshRenderer = this add MeshRenderer::class

                meshMaterialPairs[node.mMeshes()!!.get(0)].run {
                    meshRenderer.mesh = first
                    meshRenderer.material = second
                }
            }

            Array(node.mNumChildren()) { AINode.create(node.mChildren()!!.get(it)) }.forEach { aiChild ->
                val child = generateNode(aiChild)
                (child add Transform::class).parent = transform
                aiChild.free()
            }

        }

        val result = aiScene.mRootNode()?.run { generateNode(this) }

        aiScene.free()

        return result

    }

}

fun assetAt(path: String) = Asset(File(path))
fun resourceAt(path: String) = assetAt(Asset::class.java.classLoader.getResource(path).file.apply(::println))