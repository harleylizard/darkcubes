package com.harleylizard.project.gl

import com.harleylizard.project.Resources.readEntityModel
import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f
import org.lwjgl.opengl.GL45.*

class MeshModel {
	private val vao = glCreateVertexArrays()
	private val vbo = glCreateBuffers()
	private val ebo = glCreateBuffers()

	private var count: Int = 0

	init {
		MeshBuilder.use(4 * 4) {
			val empty = Matrix4f()
			val model = "assets/model/entity/player.json".readEntityModel

			model.build(empty, it)
			it.triangulate()

			count = it.count

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
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0)
		glDisableVertexArrayAttrib(vao, 0)
		glBindVertexArray(0)
	}
}