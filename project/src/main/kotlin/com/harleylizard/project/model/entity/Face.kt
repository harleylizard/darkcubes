package com.harleylizard.project.model.entity

data class Face(
	val minU: Float,
	val minV: Float,
	val maxU: Float,
	val maxV: Float) {

	fun move(x: Float, y: Float) = Face(minU + x, minV + y, maxU + x, maxV + y)
}