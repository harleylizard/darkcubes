package com.harleylizard.project.material

import com.google.gson.JsonDeserializer

class TextureSize internal constructor(val width: Int, val height: Int) {

	companion object {
		val deserializer = JsonDeserializer { json, typeOf, context ->
			val jsonObject = json.asJsonObject
			val width = jsonObject.getAsJsonPrimitive("width").asInt
			val height = jsonObject.getAsJsonPrimitive("height").asInt

			TextureSize(width, height)
		}

	}
}