package com.harleylizard.project.model.entity

import com.google.gson.JsonDeserializer
import com.harleylizard.project.model.MeshBuilder
import com.harleylizard.project.model.Shape
import org.joml.Matrix4f
import java.util.Collections

class Bone(val cubes: List<Cube>) : Shape {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder) {
		cubes
	}

	companion object {
		val deserializer = JsonDeserializer { json, typeOf, context ->
			val jsonObject = json.asJsonObject
			val cubes = jsonObject.getAsJsonArray("cubes")

			val list = mutableListOf<Cube>()
			list.addAll(cubes.map { context.deserialize(it, Cube::class.java) })
			Bone(Collections.unmodifiableList(list))
		}

	}
}