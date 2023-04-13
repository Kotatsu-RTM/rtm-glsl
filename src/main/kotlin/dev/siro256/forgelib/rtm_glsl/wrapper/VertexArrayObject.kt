package dev.siro256.forgelib.rtm_glsl.wrapper

import org.lwjgl.opengl.GL30

abstract class VertexArrayObject {
    @Suppress("MemberVisibilityCanBePrivate")
    protected val name = GL30.glGenVertexArrays()

    open fun bind() = GL30.glBindVertexArray(name)
    open fun unbind() = GL30.glBindVertexArray(0)

    init {
        if (name == 0) throw RuntimeException("glGenVertexArrays returns 0")
    }
}
