package dev.siro256.forgelib.rtm_glsl.wrapper

import dev.siro256.forgelib.rtm_glsl.BufferAllocator
import dev.siro256.forgelib.rtm_glsl.enum.GLBufferUsage
import org.lwjgl.opengl.GL15

open class IndexBufferObject(usage: GLBufferUsage, indices: IntArray) {
    @Suppress("MemberVisibilityCanBePrivate")
    protected val name = GL15.glGenBuffers()

    open fun bind() = GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, name)

    init {
        if (name == 0) throw RuntimeException("glGenBuffers returns 0")

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, name)
        GL15.glBufferData(
            GL15.GL_ELEMENT_ARRAY_BUFFER,
            BufferAllocator.createDirectIntBuffer(indices.size).put(indices).apply { rewind() },
            usage.constant
        )
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0)
    }
}
