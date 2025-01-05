package com.harleylizard.project.model.entity

import com.harleylizard.project.model.MeshBuilder
import com.harleylizard.project.model.Shape
import org.joml.Matrix4f

class Cube(
	private val fromX: Float,
	private val fromY: Float,
	private val fromZ: Float,
	private val toX: Float,
	private val toY: Float,
	private val toZ: Float
) : Shape {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder) {
		builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F)
		builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F)
		builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F)
		builder.vertex(matrix4f, 0.0F, 0.0F, 0.0F)
	}
}