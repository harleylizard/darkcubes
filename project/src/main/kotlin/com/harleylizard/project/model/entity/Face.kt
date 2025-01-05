package com.harleylizard.project.model.entity

data class Face(
	val min0: Float,
	val max0: Float,
	val min1: Float,
	val max1: Float) {

	fun move(x: Float, y: Float) = Face(min0 + x, max0 + y, min1 + x, max1 + y)
}