package dev.siro256.forgelib.rtm_glsl.format

interface VertexFormat {
    fun toFloatArray(): FloatArray

    data class Vertex(val x: Float, val y: Float, val  z: Float): VertexFormat {
        override fun toFloatArray() = floatArrayOf(x, y, z)

        companion object {
            const val SIZE_ELEMENTS = 3
            const val SIZE_BYTES = SIZE_ELEMENTS * Float.SIZE_BYTES
        }
    }

    data class VertexUV(val x: Float, val y: Float, val z: Float, val u: Float, val v: Float): VertexFormat {
        override fun toFloatArray() = floatArrayOf(x, y, z, u, v)

        companion object {
            const val SIZE_ELEMENTS = 5
            const val SIZE_BYTES = SIZE_ELEMENTS * Float.SIZE_BYTES
        }
    }

    data class VertexNormalUV(
        val vX: Float, val vY: Float, val vZ: Float,
        val nX: Float, val nY: Float, val nZ: Float,
        val u: Float, val v: Float
    ): VertexFormat {
        override fun toFloatArray() = floatArrayOf(vX, vY, vZ, nX, nY, nZ, u, v)

        companion object {
            const val SIZE_ELEMENTS = 8
            const val SIZE_BYTES = SIZE_ELEMENTS * Float.SIZE_BYTES
        }
    }
}
