package com.harleylizard.project

class Mouse {
	private var previousX = 0.0
	private var previousY = 0.0

	var cursorX: Double = 0.0
	var cursorY: Double = 0.0
	var deltaX = 0.0
	var deltaY = 0.0

	fun step(sensitivity: Double) {
		deltaX = (cursorX - previousX) * sensitivity
		deltaY = (cursorY - previousY) * sensitivity
		previousX = cursorX
		previousY = cursorY
	}
}