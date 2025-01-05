package com.harleylizard.project.scene

import com.harleylizard.project.Resources.readEntityModel
import com.harleylizard.project.Window
import com.harleylizard.project.gl.*

class PlayerCustomiseScene : Scene {
	private val pipeline = ProgramPipeline.build {
		use(Shader.FRAGMENT, "assets/shader/quad.fsh.glsl")
		use(Shader.VERTEX, "assets/shader/quad.vsh.glsl")
	}

	private val quad = MeshQuad()

	private val model = "assets/model/entity/player.json".readEntityModel

	override fun draw(window: Window, matrices: MatrixBuffer) {
		val fovy = Math.toRadians(70.0).toFloat()
		matrices.perspective(fovy, window.ratio)

		matrices.last.translate(0.0F, 0.0F, -10.0F)

		pipeline.bind()
		matrices.upload()
		quad.draw()
		ProgramPipeline.unbind()
	}
}