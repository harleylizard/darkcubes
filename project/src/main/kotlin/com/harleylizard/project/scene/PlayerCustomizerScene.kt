package com.harleylizard.project.scene

import com.harleylizard.project.Window
import com.harleylizard.project.gl.*

class PlayerCustomizerScene : Scene {
	private val pipeline = ProgramPipeline.build {
		use(Shader.FRAGMENT, "assets/shader/quad.fsh.glsl")
		use(Shader.VERTEX, "assets/shader/quad.vsh.glsl")
	}

	override fun draw(window: Window, matrices: MatrixBuffer) {
	}
}