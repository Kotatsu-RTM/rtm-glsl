package dev.siro256.forgelib.rtm_glsl.wrapper

import dev.siro256.forgelib.rtm_glsl.BufferAllocator
import dev.siro256.forgelib.rtm_glsl.enum.GLBufferUsage
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL43

abstract class VertexBufferObject private constructor(private val stride: Int) {
    @Suppress("MemberVisibilityCanBePrivate")
    protected val name = GL15.glGenBuffers()

    open fun bind(index: Int) = GL43.glBindVertexBuffer(index, name, 0, stride)

    constructor(usage: GLBufferUsage, data: FloatArray, stride: Int): this(stride) {
        if (name == 0) throw RuntimeException("glGenBuffers returns 0")

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, name)
        GL15.glBufferData(
            GL15.GL_ARRAY_BUFFER,
            BufferAllocator.createDirectFloatBuffer(data.size).put(data).apply { rewind() },
            usage.constant
        )
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    constructor(usage: GLBufferUsage, data: IntArray, stride: Int): this(stride) {
        if (name == 0) throw RuntimeException("glGenBuffers returns 0")

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, name)
        GL15.glBufferData(
            GL15.GL_ARRAY_BUFFER,
            BufferAllocator.createDirectIntBuffer(data.size).put(data).apply { rewind() },
            usage.constant
        )
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }

    constructor(usage: GLBufferUsage, data: ByteArray, stride: Int): this(stride) {
        if (name == 0) throw RuntimeException("glGenBuffers returns 0")

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, name)
        GL15.glBufferData(
            GL15.GL_ARRAY_BUFFER,
            BufferAllocator.createDirectBuffer(data.size).put(data).apply { rewind() },
            usage.constant
        )
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }
}
