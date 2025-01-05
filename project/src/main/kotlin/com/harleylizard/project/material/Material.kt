package com.harleylizard.project.material

import com.google.gson.JsonDeserializer

class Material(
	private val width: Int,
	private val height: Int
) {

	companion object {
		private val names = listOf("color", "emission", "normal", "mask", "specular")

		val deserializer = JsonDeserializer { json, typeOf, context ->
			val jsonObject = json.asJsonObject
			val textureSize = jsonObject.getAsJsonArray("texture_size")

			Material(textureSize.get(0).asInt, textureSize.get(1).asInt)
		}

	}
}