package com.harleylizard.project.model.entity

import com.google.gson.JsonDeserializer
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