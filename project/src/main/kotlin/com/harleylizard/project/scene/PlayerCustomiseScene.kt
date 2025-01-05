package com.harleylizard.project.scene

import com.harleylizard.project.Window
import com.harleylizard.project.gl.*
import org.lwjgl.opengl.GL11.GL_CULL_FACE
import org.lwjgl.opengl.GL11.glEnable

class PlayerCustomiseScene : Scene {
	private val pipeline = ProgramPipeline.build {
		use(Shader.FRAGMENT, "assets/shader/quad.fsh.glsl")
		use(Shader.VERTEX, "assets/shader/quad.vsh.glsl")
	}

	private val quad = MeshModel()

	init {
		glEnable(GL_CULL_FACE)
	}

	override fun draw(window: Window, matrices: MatrixBuffer) {
		val fovy = Math.toRadians(70.0).toFloat()
		matrices.perspective(fovy, window.ratio)

		matrices.last.translate(0.0F, 0.0F, -7.0F)

		pipeline.bind()
		matrices.upload()
		quad.draw()
		ProgramPipeline.unbind()
	}
}