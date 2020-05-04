package opengl

data class DeviceState(
    val readFramebuffer: Framebuffer? = null,
    val writeFramebuffer: Framebuffer? = null,

    val winding: FaceWinding = FaceWinding.CounterClockWise,

//    val cull: Boolean,
    val cullFunc: CullMode? = CullMode.Back,

//    val blend: Boolean,
    val blendFunction: BlendFunc? = BlendFunc.Zero,

//    val depth: Boolean,
    val depthFunction: DepthFunc? = DepthFunc.Less,

    val stencilFunc: Unit? = null,
    val stencilMask: UInt = 0xFFFFFFFFU,
    val stencilOp: Unit = Unit
)