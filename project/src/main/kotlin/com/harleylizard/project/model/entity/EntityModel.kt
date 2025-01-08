package com.harleylizard.project.model.entity

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.harleylizard.project.material.TextureSize
import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f
import java.util.*

class EntityModel(private val bones: Map<String, Bone>, private val size: TextureSize) {

	fun build(matrix4f: Matrix4f, builder: MeshBuilder) {
		bones.values.flatMap(Bone::cubes).forEach { it.build(matrix4f, builder, size.width, size.height) }
	}

	companion object {
		val deserializer = JsonDeserializer { json, typeOf, context ->
			val jsonObject = json.asJsonObject
			val bones = jsonObject.getAsJsonObject("bones")
			val size = context.deserialize<TextureSize>(jsonObject.get("texture_size"), TextureSize::class.java)

			val map = mutableMapOf<String, Bone>()
			for (entry in bones.entrySet()) {
				map[entry.key] = context.deserialize<Bone>(entry.value, Bone::class.java)
			}
			EntityModel(Collections.unmodifiableMap(map), size)
		}

		inline val gson; get() = GsonBuilder()
			.registerTypeAdapter(EntityModel::class.java, EntityModel.deserializer)
			.registerTypeAdapter(Bone::class.java, Bone.deserializer)
			.registerTypeAdapter(Cube::class.java, Cube.deserializer)
			.registerTypeAdapter(TextureSize::class.java, TextureSize.deserializer)
			.create()

	}
}