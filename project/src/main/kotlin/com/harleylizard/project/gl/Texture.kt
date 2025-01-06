package com.harleylizard.project.gl

import com.harleylizard.project.Main.memAddress
import com.harleylizard.project.Resources.readAsImage
import org.lwjgl.opengl.GL45.*
import org.lwjgl.stb.STBImage.nstbi_image_free
import org.lwjgl.stb.STBImage.nstbi_load_from_memory
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.memFree

object Texture {

	val String.asTexture; get() = MemoryStack.stackPush().use {
		val texture = glCreateTextures(GL_TEXTURE_2D)
		glTextureParameteri(texture, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
		glTextureParameteri(texture, GL_TEXTURE_MIN_FILTER, GL_NEAREST)

		val buffer = it.callocInt(3)
		val image = this.readAsImage
		val pixels = nstbi_load_from_memory(image.memAddress, image.remaining(),
			buffer.memAddress,
			buffer.memAddress + 4,
			buffer.memAddress + 8, 4)

		val width = buffer[0]
		val height = buffer[1]

		glTextureStorage2D(texture, 1, GL_RGBA8, width, height)
		glTextureSubImage2D(texture, 0, 0, 0, width, height, GL_RGBA, GL_UNSIGNED_BYTE, pixels)

		memFree(image)
		nstbi_image_free(pixels)
		texture
	}
}