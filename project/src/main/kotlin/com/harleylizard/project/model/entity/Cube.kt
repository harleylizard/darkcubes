package com.harleylizard.project.model.entity

import com.google.gson.JsonDeserializer
import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f

class Cube(
	val fromX: Float,
	val fromY: Float,
	val fromZ: Float,
	val toX: Float,
	val toY: Float,
	val toZ: Float
) : Buildable {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder, size: FlatSize) {
		val j = fromX + toX
		val k = fromY + toY
		val m = fromZ + toZ

		var (min0, max0, min1, max1) = size.move(size.northFace(this), 0, 0)
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