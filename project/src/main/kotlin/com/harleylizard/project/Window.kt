package com.harleylizard.project

import com.harleylizard.project.Main.memAddress
import com.harleylizard.project.Main.throwIf
import com.harleylizard.project.input.Keys
import com.harleylizard.project.input.Mouse
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL45.glViewport
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.NULL

class Window(private var width: Int, private var height: Int) {
	val mouse = Mouse()
	val keys = Keys()

	private val window: Long

	val shouldClose; get() = glfwWindowShouldClose(window)

	val ratio; get() = width.toFloat() / height.toFloat()

	init {
		glfwDefaultWindowHints()
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6)
		glfwWindowHint(GLFW_CONTEXT_RELEASE_BEHAVIOR, GLFW_RELEASE_BEHAVIOR_FLUSH)
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)

		glfwCreateWindow(width, height, "Darkcubes", NULL, NULL).also { window = it }.throwIf(::RuntimeException) { it == NULL }
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

		glfwSetWindowSizeCallback(window) { window, width, height ->
			this.width = width
			this.height = height
			glViewport(0, 0, width, height)
		}

		glfwSetCursorPosCallback(window) { window, x, y ->
			mouse.cursorX = x
			mouse.cursorY = y
		}
		glfwSetMouseButtonCallback(window) { window, button, action, mods ->
			keys.set(button, action != GLFW_RELEASE)
		}
		glfwSetKeyCallback(window) { window, key, scancode, action, mods ->
			keys.set(key, action != GLFW_RELEASE)
		}
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