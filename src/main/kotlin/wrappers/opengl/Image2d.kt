package wrappers.opengl

class Image2d(override val texture: Texture2d, val index: Int) : Image() {

    val internalFormat get() = texture.internalFormat

    val width get() = texture.width / (index + 1)

    val height get() = texture.height / (index + 1)

}