package dev.siro256.forgelib.rtm_glsl.wrapper

import org.lwjgl.opengl.GL30

class VertexArrayObject {
    private val name = GL30.glGenVertexArrays()

    fun <R> use(block: () -> R): R {
        GL30.glBindVertexArray(name)
        val r = block.invoke()
        GL30.glBindVertexArray(0)
        return r
    }

    init {
        if (name == 0) throw RuntimeException("glGenVertexArrays returns 0")
    }
}
