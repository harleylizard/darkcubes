package com.harleylizard.project.material

import com.google.gson.JsonDeserializer
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps

class Material(private val map: Int2ObjectMap<String>, private val size: TextureSize) {

	companion object {
		private val names = listOf("color", "emission", "normal", "mask", "specular")

		val deserializer = JsonDeserializer { json, typeOf, context ->
			val jsonObject = json.asJsonObject
			val textures = jsonObject.getAsJsonObject("textures")
			val size = jsonObject.getAsJsonObject("texture_size").let {
				TextureSize(
					it.getAsJsonPrimitive("width").asInt,
					it.getAsJsonPrimitive("height").asInt)

			}

			Material(Int2ObjectMaps.emptyMap(), size)
		}

	}
}