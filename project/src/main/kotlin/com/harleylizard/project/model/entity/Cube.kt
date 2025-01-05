package com.harleylizard.project.model.entity

import com.google.gson.JsonDeserializer
import com.harleylizard.project.material.Material
import com.harleylizard.project.model.Buildable
import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f

class Cube(
	private val fromX: Float,
	private val fromY: Float,
	private val fromZ: Float,
	private val toX: Float,
	private val toY: Float,
	private val toZ: Float
) : Buildable {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder, material: Material) {
		val j = fromX + toX
		val k = fromY + toY
		val m = fromZ + toZ

		builder.vertex(matrix4f, j, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, k, fromZ, 0.0F, 0.0F)

		builder.vertex(matrix4f, fromX, fromY, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, fromY, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, k, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, m, 0.0F, 0.0F)

		builder.vertex(matrix4f, j, fromY, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, k, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, k, m, 0.0F, 0.0F)

		builder.vertex(matrix4f, fromX, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, fromY, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, fromZ, 0.0F, 0.0F)

		builder.vertex(matrix4f, j, k, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, k, m, 0.0F, 0.0F)

		builder.vertex(matrix4f, j, fromY, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, fromY, m, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, fromY, fromZ, 0.0F, 0.0F)
	}

	companion object {
		val deserializer = JsonDeserializer { json, typeOf, context ->
			val jsonObject = json.asJsonObject
			val from = jsonObject.getAsJsonArray("from")
			val to = jsonObject.getAsJsonArray("to")

			Cube(
				from[0].asFloat / 16.0F,
				from[1].asFloat / 16.0F,
				from[2].asFloat / 16.0F,
				to[0].asFloat / 16.0F,
				to[1].asFloat / 16.0F,
				to[2].asFloat / 16.0F
			)
		}

	}
}