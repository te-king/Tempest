package wrappers.opengl

class DeviceState(
    val readFramebuffer: Framebuffer? = null,
    val writeFramebuffer: Framebuffer? = null,

    val cull: Boolean,
    val cullFunc: CullMode = CullMode.BACK,
    val winding: FaceWinding = FaceWinding.CCW,

    val blend: Boolean,
    val blendFunction: BlendFunc = BlendFunc.Zero,

    val depth: Boolean,
    val depthFunction: DepthFunc = DepthFunc.Less,

    val stencil: Boolean,
    val stencilMask: UInt = 0xFFFFFFFFU,
    val stencilOp: Unit = Unit,
    val stencilFunc: Unit = Unit
)