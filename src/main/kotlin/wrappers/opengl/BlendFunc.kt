package wrappers.opengl

import org.lwjgl.opengl.GL45C.*

enum class BlendFunc(val native: Int) {
    Zero(GL_ZERO),
    One(GL_ONE),
    SourceColor(GL_SRC_COLOR),
    OneMinusSourceColor(GL_ONE_MINUS_SRC_COLOR),
    DestinationColor(GL_DST_COLOR),
    OneMinusDestinationColor(GL_ONE_MINUS_DST_COLOR),
    SourceAlpha(GL_SRC_ALPHA),
    OneMinusSourceAlpha(GL_ONE_MINUS_SRC_ALPHA),
    DestinationAlpha(GL_DST_ALPHA),
    OneMinusDestinationAlpha(GL_ONE_MINUS_DST_ALPHA),
    ConstantColor(GL_CONSTANT_COLOR),
    OneMinusConstantColor(GL_ONE_MINUS_CONSTANT_COLOR),
    ConstantAlpha(GL_CONSTANT_ALPHA),
    OneMinusConstantAlpha(GL_ONE_MINUS_CONSTANT_ALPHA),
    AlphaSaturate(GL_SRC_ALPHA_SATURATE),
    SourceOneColor(GL_SRC1_COLOR),
    OneMinusSourceOneColor(GL_ONE_MINUS_SRC1_COLOR),
    SourceOneAlpha(GL_SRC1_ALPHA),
    OneMinusSourceOneAlpha(GL_ONE_MINUS_SRC1_ALPHA),
}
