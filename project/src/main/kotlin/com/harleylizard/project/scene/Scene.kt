package com.harleylizard.project.scene

import com.harleylizard.project.Window
import com.harleylizard.project.gl.MatrixBuffer

sealed interface Scene {

	fun draw(window: Window, matrices: MatrixBuffer)
}