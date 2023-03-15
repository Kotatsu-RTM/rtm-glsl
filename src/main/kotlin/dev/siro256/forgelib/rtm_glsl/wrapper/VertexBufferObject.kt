package dev.siro256.forgelib.rtm_glsl.wrapper

import dev.siro256.forgelib.rtm_glsl.BufferAllocator
import dev.siro256.forgelib.rtm_glsl.enum.GLBufferUsage
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL43

class VertexBufferObject(usage: GLBufferUsage, vertices: FloatArray, private val stride: Int) {
    private val name = GL15.glGenBuffers()

    fun bind(index: Int) = GL43.glBindVertexBuffer(index, name, 0, stride)

    init {
        if (name == 0) throw RuntimeException("glGenBuffers returns 0")

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, name)
        GL15.glBufferData(
            GL15.GL_ARRAY_BUFFER,
            BufferAllocator.createDirectFloatBuffer(vertices.size).put(vertices),
            usage.constant
        )
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
    }
}
