package com.harleylizard.project.gl

import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f
import org.lwjgl.opengl.GL45.*

class MeshQuad {
	private val vao = glCreateVertexArrays()
	private val vbo = glCreateBuffers()
	private val ebo = glCreateBuffers()

	init {
		MeshBuilder.use(4 * 4) {
			val empty = Matrix4f()
			it.vertex(empty, -0.5f, -0.5f, 0.0f)
			it.vertex(empty,  0.5f, -0.5f, 0.0f)
			it.vertex(empty,  0.5f,  0.5f, 0.0f)
			it.vertex(empty, -0.5f,  0.5f, 0.0f)
			it.triangulate()

			glVertexArrayVertexBuffer(vao, 0, vbo, 0, 16)
			glVertexArrayAttribBinding(vao, 0, 0)
			glVertexArrayAttribFormat(vao, 0, 4, GL_FLOAT, false, 0)
			glVertexArrayElementBuffer(vao, ebo)
			val flag = 0
			glNamedBufferStorage(vbo, it.vertices, flag)
			glNamedBufferStorage(ebo, it.elements, flag)
		}
	}

	fun draw() {
		glBindVertexArray(vao)
		glEnableVertexArrayAttrib(vao, 0)
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)
		glDisableVertexArrayAttrib(vao, 0)
		glBindVertexArray(0)
	}
}