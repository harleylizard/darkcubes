package com.harleylizard.project

import com.harleylizard.project.gl.MatrixBuffer
import com.harleylizard.project.scene.PlayerCustomiseScene
import org.lwjgl.glfw.GLFW.glfwInit
import org.lwjgl.glfw.GLFW.glfwTerminate
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.memAddress
import java.nio.Buffer

object Main {

	@JvmStatic
	fun main(args: Array<String>) {
		throwIf(::RuntimeException) { !glfwInit() }

		val window = Window(854, 480)

		val scene = PlayerCustomiseScene()
		val matrices = MatrixBuffer.of()

		glClearColor(0.0F, 0.75F, 0.75F, 0.0F)
		while (!window.shouldClose) {
			glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
			scene.draw(window, matrices)

			window.update()
		}
		window.destroy()

		glfwTerminate()
	}

	inline fun <T, E : Exception> T.throwIf(unit: () -> E, predicate: (T) -> Boolean) {
		if (predicate(this)) throw unit()
	}

	inline val Buffer.memAddress; get() = memAddress(this)
}