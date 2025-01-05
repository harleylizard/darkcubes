package com.harleylizard.project.gl

import org.lwjgl.opengl.GL43.*

enum class Shader(val bit: Int, val type: Int) {
	FRAGMENT(GL_FRAGMENT_SHADER_BIT, GL_FRAGMENT_SHADER),
	VERTEX(GL_VERTEX_SHADER_BIT, GL_VERTEX_SHADER)

}