package com.harleylizard.project

import com.harleylizard.project.model.entity.EntityModel
import org.lwjgl.system.MemoryUtil.memCalloc
import org.lwjgl.system.MemoryUtil.memRealloc
import java.io.*
import java.nio.channels.Channels

object Resources {
	@get:Throws(IOException::class)
	private val InputStream.asReader; get() = BufferedReader(InputStreamReader(this))

	@get:Throws(IOException::class)
	private val String.open; get() = Resources::class.java.classLoader.getResource(this)?.openStream() ?: throw RuntimeException("Unable to find resource $this")

	@get:Throws(IOException::class)
	val String.readLines; get() = open.asReader.use { reader ->
		val builder = StringBuilder()

		var line: String? = null
		while (reader.readLine()?.also { line = it } != null) {
			builder.append(line).append("\n")
		}
		builder.toString()
	}

	@get:Throws(IOException::class)
	val String.readAsImage; get() = Channels.newChannel(open).use { channel ->
		var buffer = memCalloc(1024 * 4)

		while(channel.read(buffer) != -1) {
			buffer.takeIf { it.remaining() < 0 }?.also { buffer = memRealloc(it, it.capacity() * 2) }
		}
		buffer.flip()
	}

	@get:Throws(IOException::class)
	val String.readEntityModel; get() = open.asReader.use {
		EntityModel.gson.fromJson(it, EntityModel::class.java)
	}
}