package com.harleylizard.project.scene

import com.harleylizard.project.Window
import com.harleylizard.project.gl.*
import com.harleylizard.project.gl.Texture.asTexture
import org.joml.Quaternionf
import org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT
import org.lwjgl.opengl.GL45.*

class CustomizerScene : Scene {
	private val pipeline = ProgramPipeline.build {
		use(Shader.FRAGMENT, "assets/shader/player.fsh.glsl")
		use(Shader.VERTEX, "assets/shader/player.vsh.glsl")
	}

	private val quad = MeshModel()
	private val rotation = Quaternionf()

	private var yaw = 0.0
	private var pitch = 0.0
	private var yawForce = 0.0
	private var pitchForce = 0.0

	init {
		glEnable(GL_CULL_FACE)
		glEnable(GL_DEPTH_TEST)
		glBindTextureUnit(0, "assets/texture/entity/player.png".asTexture)
	}

	override fun draw(window: Window, matrices: MatrixBuffer) {
		val keys = window.keys
		val mouse = window.mouse

		mouse.step(0.01)
		if (keys.isHeld(GLFW_MOUSE_BUTTON_RIGHT)) {
			yawForce += mouse.deltaX
			pitchForce += mouse.deltaY
		}

		val drag = 0.75
		yawForce *= drag
		pitchForce *= drag

		pitch += pitchForce
		yaw += yawForce

		val angle = Math.toRadians(90.0)
		pitch = Math.clamp(pitch, 0.0, angle)

		rotation.identity()
		rotation.rotateX(pitch.toFloat())
		rotation.rotateY(yaw.toFloat())

		val fovy = Math.toRadians(70.0).toFloat()
		matrices.perspective(fovy, window.ratio)

		val last = matrices.last
		last.translate(0.0F, 0.0F, -7.0F)
		last.rotate(rotation)

		pipeline.bind()
		matrices.upload()
		quad.draw()
		ProgramPipeline.unbind()
	}
}