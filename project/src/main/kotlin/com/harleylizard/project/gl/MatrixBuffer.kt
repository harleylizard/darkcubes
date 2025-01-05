package com.harleylizard.project.gl

import org.joml.Matrix4f
import org.lwjgl.opengl.GL45.*

class MatrixBuffer private constructor() {
	private val projection = Matrix4f()
	private val pose = Matrix4f()

	private val ubo = glCreateBuffers()

	val last: Matrix4f; get() = pose.identity()

	init {
		glNamedBufferStorage(ubo, BYTE_SIZE, GL_DYNAMIC_STORAGE_BIT or GL_MAP_READ_BIT or GL_MAP_WRITE_BIT)
		glBindBufferBase(GL_UNIFORM_BUFFER, 0, ubo)
	}

	fun perspective(fovy: Float, ratio: Float) {
		projection.identity()
		projection.perspective(fovy, ratio, 0.01F, 100.0F)
	}

	fun upload() {
		glMapNamedBuffer(ubo, GL_READ_WRITE)?.also {
			projection[0, it]
			pose[16 * 4, it]
			glUnmapNamedBuffer(ubo)
		}
	}

	companion object {
		private const val BYTE_SIZE = 16L * 4L * 2L

		fun of() = MatrixBuffer()
	}
}