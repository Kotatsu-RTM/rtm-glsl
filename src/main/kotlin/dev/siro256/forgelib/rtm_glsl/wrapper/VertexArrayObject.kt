package dev.siro256.forgelib.rtm_glsl.wrapper

import org.lwjgl.opengl.GL30

class VertexArrayObject {
    private val name = GL30.glGenVertexArrays()

    fun bind() = GL30.glBindVertexArray(name)
    fun unbind() = GL30.glBindVertexArray(0)

    init {
        if (name == 0) throw RuntimeException("glGenVertexArrays returns 0")
    }
}
