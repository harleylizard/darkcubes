package com.harleylizard.project.scene

import com.harleylizard.project.Window
import com.harleylizard.project.gl.*
import com.harleylizard.project.gl.Texture.asTexture
import org.lwjgl.opengl.GL45.*

class PlayerCustomiseScene : Scene {
	private val pipeline = ProgramPipeline.build {
		use(Shader.FRAGMENT, "assets/shader/player.fsh.glsl")
		use(Shader.VERTEX, "assets/shader/player.vsh.glsl")
	}

	private val quad = MeshModel()

	init {
		glEnable(GL_CULL_FACE)
		glBindTextureUnit(0, "assets/texture/entity/player.png".asTexture)
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