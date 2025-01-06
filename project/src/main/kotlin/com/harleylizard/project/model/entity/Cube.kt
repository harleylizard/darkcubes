package com.harleylizard.project.model.entity

import com.google.gson.JsonDeserializer
import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f

class Cube(
	private val fromX: Float,
	private val fromY: Float,
	private val fromZ: Float,
	private val toX: Float,
	private val toY: Float,
	private val toZ: Float,
	private val u: Int,
	private val v: Int
) : Buildable {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder, size: TextureSize) {
		val j = fromX + toX
		val k = fromY + toY
		val m = fromZ + toZ

		val (width, height) = size
		val m00 = ((j - fromX) * 16.0F) / width
		val m01 = ((m - fromZ) * 16.0F) / width
		val m10 = ((k - fromY) * 16.0F) / height
		val m11 = ((m - fromZ) * 16.0F) / height
		val l = u.toFloat() / width
		val n = v.toFloat() / height

		var uvs = Face(0.0F, 1.0F, 0.0F, 1.0F)
		builder.vertex(matrix4f, j, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, fromY, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, fromX, k, fromZ, 0.0F, 0.0F)
		builder.vertex(matrix4f, j, k, fromZ, 0.0F, 0.0F)

		uvs = Face(m01 + m00 + m01, m11, m01 + m00 + m01 + m00, m11 + m10).move(l, n)
		builder.vertex(matrix4f, fromX, fromY, m, uvs.minU, uvs.maxV)
		builder.vertex(matrix4f, j, fromY, m, uvs.maxU, uvs.maxV)
		builder.vertex(matrix4f, j, k, m, uvs.maxU, uvs.minV)
		builder.vertex(matrix4f, fromX, k, m, uvs.minU, uvs.minV)

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
			val uv = jsonObject.getAsJsonArray("uv")

			Cube(
				from[0].asFloat / 16.0F,
				from[1].asFloat / 16.0F,
				from[2].asFloat / 16.0F,
				to[0].asFloat / 16.0F,
				to[1].asFloat / 16.0F,
				to[2].asFloat / 16.0F,
				uv[0].asInt,
				uv[1].asInt
			)
		}

	}
}