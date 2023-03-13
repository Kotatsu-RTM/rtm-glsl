package dev.siro256.forgelib.rtm_glsl

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

object BufferAllocator {
    fun createDirectBuffer(capacity: Int): ByteBuffer =
        ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder())

    fun createDirectIntBuffer(capacity: Int): IntBuffer =
        createDirectBuffer(capacity.shl(2)).asIntBuffer()

    fun createDirectFloatBuffer(capacity: Int): FloatBuffer =
        createDirectBuffer(capacity.shl(2)).asFloatBuffer()
}
