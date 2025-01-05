package com.harleylizard.project

import com.harleylizard.project.Main.memAddress
import com.harleylizard.project.Main.throwIf
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.NULL

class Window(width: Int, height: Int) {
	private val window: Long

	val shouldClose; get() = glfwWindowShouldClose(window)

	init {
		glfwDefaultWindowHints()
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6)
		glfwWindowHint(GLFW_CONTEXT_RELEASE_BEHAVIOR, GLFW_RELEASE_BEHAVIOR_FLUSH)
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)

		glfwCreateWindow(width, height, "Project", NULL, NULL).also { window = it }.throwIf(::RuntimeException) { it == NULL }
		MemoryStack.stackPush().use {
			val buffer = it.callocInt(6)

			nglfwGetMonitorWorkarea(glfwGetPrimaryMonitor(),
				buffer.memAddress,
				buffer.memAddress + 4,
				buffer.memAddress + 8,
				buffer.memAddress + 12)
			nglfwGetWindowSize(window, buffer.memAddress + 16, buffer.memAddress + 20)

			val x = (buffer[2] - (buffer[4])) / 2
			val y = (buffer[3] - (buffer[5])) / 2
			glfwSetWindowPos(window, x, y)
		}

		glfwMakeContextCurrent(window)
		GL.createCapabilities()
	}

	fun update() {
		glfwSwapBuffers(window)
		glfwPollEvents()
	}

	fun destroy() {
		glfwFreeCallbacks(window)
		glfwDestroyWindow(window)
	}
}