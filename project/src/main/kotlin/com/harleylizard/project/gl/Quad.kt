package com.harleylizard.project.gl

import org.lwjgl.opengl.GL45.*

class Quad {
	private val vao = glCreateVertexArrays()
	private val vbo = glCreateBuffers()
	private val ebo = glCreateBuffers()

	init {
		val vertices = floatArrayOf(
			-0.5f, -0.5f, 0.0f, 1.0f, 0.5f, -0.5f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 1.0f, -0.5f, 0.5f, 0.0f, 1.0f
		)
		val elements = intArrayOf(
			0, 1, 2, 2, 3, 0
		)

		glVertexArrayVertexBuffer(vao, 0, vbo, 0, 16)
		glVertexArrayAttribBinding(vao, 0, 0)
		glVertexArrayAttribFormat(vao, 0, 4, GL_FLOAT, false, 0)

		glVertexArrayElementBuffer(vao, ebo)

		val flag = 0
		glNamedBufferStorage(vbo, vertices, flag)
		glNamedBufferStorage(ebo, elements, flag)
	}

	fun draw() {
		glBindVertexArray(vao)
		glEnableVertexArrayAttrib(vao, 0)
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)
		glDisableVertexArrayAttrib(vao, 0)
		glBindVertexArray(0)
	}
}