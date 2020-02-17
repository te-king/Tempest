package wrappers.opengl

class Image<F : TextureFormat, K : TextureKind>(val texture: Texture<F, K>, val index: Int) {

    val width get() = texture.width / (index + 1)

    val height get() = texture.height / (index + 1)

    val depth get() = texture.depth / (index + 1)

}