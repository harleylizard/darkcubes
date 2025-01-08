package com.harleylizard.project.gl

import com.harleylizard.project.Main.memAddress
import com.harleylizard.project.Resources.readAsImage
import com.harleylizard.project.material.TextureSize
import org.lwjgl.opengl.GL45.*
import org.lwjgl.stb.STBImage.nstbi_image_free
import org.lwjgl.stb.STBImage.nstbi_load_from_memory
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.memFree

class TextureArray private constructor(private val size: TextureSize) {
	private val list = mutableListOf<String>()

	val compile: Int; get() = MemoryStack.stackPush().use {
		val texture = glCreateTextures(GL_TEXTURE_2D_ARRAY)
		glTextureParameteri(texture, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST)
		glTextureParameteri(texture, GL_TEXTURE_MAG_FILTER, GL_NEAREST)

		val length = list.size
		glTextureStorage3D(texture, 1, GL_RGBA8, size.width, size.height, length)

		val buffer = it.callocInt(3)

		for (i in 0..<length) {
			val image = list[i].readAsImage
			val pixels = nstbi_load_from_memory(image.memAddress, image.remaining(),
				buffer.memAddress,
				buffer.memAddress + 4,
				buffer.memAddress + 8, 4)

			val width = buffer[0]
			val height = buffer[1]
			glTextureSubImage3D(texture, 0, 0, 0, i, width, height, 1, GL_RGBA, GL_UNSIGNED_BYTE, pixels)

			nstbi_image_free(pixels)
			memFree(image)
		}

		glGenerateTextureMipmap(texture)
		texture
	}

	operator fun plusAssign(texture: String) {
		if (!list.contains(texture)) {
			list += texture
		}
	}

	operator fun get(texture: String) = list.indexOf(texture)

	companion object {

		fun of(size: TextureSize) = TextureArray(size)
	}
}