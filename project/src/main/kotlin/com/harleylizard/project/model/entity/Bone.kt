package com.harleylizard.project.model.entity

import com.google.gson.JsonDeserializer
import java.util.*

class Bone(val cubes: List<Cube>) {

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