package dev.siro256.forgelib.rtm_glsl.format

interface VertexFormat {
    val size: Int
    fun toFloatArray(): FloatArray

    data class Vertex(val x: Float, val y: Float, val  z: Float): VertexFormat {
        override val size = 3
        override fun toFloatArray() = floatArrayOf(x, y, z)
    }

    data class VertexUV(val x: Float, val y: Float, val z: Float, val u: Float, val v: Float): VertexFormat {
        override val size = 5
        override fun toFloatArray() = floatArrayOf(x, y, z, u, v)
    }

    data class VertexNormalUV(
        val vX: Float, val vY: Float, val vZ: Float,
        val nX: Float, val nY: Float, val nZ: Float,
        val u: Float, val v: Float
    ): VertexFormat {
        override val size = 8
        override fun toFloatArray() = floatArrayOf(vX, vY, vZ, nX, nY, nZ, u, v)
    }
}
