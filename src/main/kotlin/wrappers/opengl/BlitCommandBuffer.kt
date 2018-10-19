package wrappers.opengl

import math.Int4
import org.lwjgl.opengl.GL45C.*

class BlitCommandBuffer(device: Device): Device.CommandBuffer(device) {

    fun copyFramebuffer(src: Framebuffer?, srcRect: Int4, dst: Framebuffer?, dstRect: Int4, mask: CopyFramebufferMask, filter: CopyFramebufferFilter) = commands.add { glBlitNamedFramebuffer(src?.id ?: 0, dst?.id ?: 0, srcRect.x, srcRect.y, srcRect.x + srcRect.z, srcRect.y + srcRect.w, dstRect.x, dstRect.y, dstRect.x + dstRect.z, dstRect.y + dstRect.w, mask.native, filter.native) }
    fun copyTexture() {}
    fun copyBuffer() {}

    fun generateMipMaps() {}

}