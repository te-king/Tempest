package wrappers.opengl

class Image1d(override val texture: Texture1d, val index: Int) : Image() {

    val internalFormat get() = texture.internalFormat

    val width get() = texture.width / (index + 1)

}