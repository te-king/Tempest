package opengl


class ElementBuffer(val buffer: Buffer<ElementArrayBuffer, *>, val indexCount: Int, val indexType: IndexType, val primitiveType: PrimitiveType)