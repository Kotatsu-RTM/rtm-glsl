package dev.siro256.forgelib.rtm_glsl.shader

import org.apache.logging.log4j.LogManager
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20

abstract class Shader<T: Shader.EntryPoint>(vertexShaderSource: String, fragmentShaderSource: String) {
    private val name = GL20.glCreateProgram()
    protected abstract val entryPoint: T

    fun use(): T {
        GL20.glUseProgram(name)
        preUse()

        return entryPoint
    }

    protected abstract fun preUse()

    protected abstract fun postUse()

    init {
        if (name == 0) throw RuntimeException("glCreateProgram returns 0")

        val logger = LogManager.getLogger("GLSL")

        val vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER)
        val fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER)

        if (vertexShader == 0 || fragmentShader == 0) throw RuntimeException("glCreateShader returns 0")

        GL20.glShaderSource(vertexShader, vertexShaderSource)
        GL20.glShaderSource(fragmentShader, fragmentShaderSource)
        GL20.glCompileShader(vertexShader)
        GL20.glCompileShader(fragmentShader)

        logger.debug("""
            vertex shader compilation log:
            ${GL20.glGetShaderInfoLog(vertexShader, GL20.glGetShaderi(vertexShader, GL20.GL_INFO_LOG_LENGTH))}
        """.trimIndent())
        logger.debug("""
            fragment shader compilation log:
            ${GL20.glGetShaderInfoLog(fragmentShader, GL20.glGetShaderi(fragmentShader, GL20.GL_INFO_LOG_LENGTH))}
        """.trimIndent())

        GL20.glAttachShader(name, vertexShader)
        GL20.glAttachShader(name, fragmentShader)
        GL20.glLinkProgram(name)

        logger.debug("""
            program link log:
              status: ${GL20.glGetProgrami(name, GL20.GL_LINK_STATUS) == GL11.GL_TRUE}
              log: ${GL20.glGetProgramInfoLog(name, GL20.glGetProgrami(name, GL20.GL_INFO_LOG_LENGTH))}
        """.trimIndent())
    }

    abstract class EntryPoint(private val shader: Shader<*>) {
        fun end() {
            shader.postUse()
            GL20.glUseProgram(0)
        }
    }
}
