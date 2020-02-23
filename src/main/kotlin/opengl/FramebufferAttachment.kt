package opengl

import org.lwjgl.opengl.GL30C.*

enum class FramebufferAttachment(val native: Int) {
    Color0(GL_COLOR_ATTACHMENT0),
    Color1(GL_COLOR_ATTACHMENT1),
    Color2(GL_COLOR_ATTACHMENT2),
    Color3(GL_COLOR_ATTACHMENT3),
    Color4(GL_COLOR_ATTACHMENT4),
    Color5(GL_COLOR_ATTACHMENT5),
    Color6(GL_COLOR_ATTACHMENT6),
    Color7(GL_COLOR_ATTACHMENT7),
    Color8(GL_COLOR_ATTACHMENT8),
    Color9(GL_COLOR_ATTACHMENT9),
    Color10(GL_COLOR_ATTACHMENT10),
    Color11(GL_COLOR_ATTACHMENT11),
    Color12(GL_COLOR_ATTACHMENT12),
    Color13(GL_COLOR_ATTACHMENT13),
    Color14(GL_COLOR_ATTACHMENT14),
    Color15(GL_COLOR_ATTACHMENT15),
    Color16(GL_COLOR_ATTACHMENT16),
    Color17(GL_COLOR_ATTACHMENT17),
    Color18(GL_COLOR_ATTACHMENT18),
    Color19(GL_COLOR_ATTACHMENT19),
    Color20(GL_COLOR_ATTACHMENT20),
    Color21(GL_COLOR_ATTACHMENT21),
    Color22(GL_COLOR_ATTACHMENT22),
    Color23(GL_COLOR_ATTACHMENT23),
    Color24(GL_COLOR_ATTACHMENT24),
    Color25(GL_COLOR_ATTACHMENT25),
    Color26(GL_COLOR_ATTACHMENT26),
    Color27(GL_COLOR_ATTACHMENT27),
    Color28(GL_COLOR_ATTACHMENT28),
    Color29(GL_COLOR_ATTACHMENT29),
    Color30(GL_COLOR_ATTACHMENT30),
    Color31(GL_COLOR_ATTACHMENT31),
    Depth(GL_DEPTH_ATTACHMENT),
    Stencil(GL_STENCIL_ATTACHMENT),
    DepthStencil(GL_DEPTH_STENCIL_ATTACHMENT)
}