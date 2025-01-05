package com.harleylizard.project.model

import org.joml.Matrix4f
import org.joml.Vector4f
import org.lwjgl.system.MemoryUtil.*
import java.nio.ByteBuffer

class MeshBuilder(private val size: Int) {
	private var buffer = memCalloc(INITIAL_LENGTH)
	private var verticesLength = 0
	private var elementsLength = 0

	private inline val quads; get() = verticesLength * size

	val vertices: ByteBuffer; get() = buffer.position(0).limit(verticesLength)

	val elements: ByteBuffer; get() = buffer.position(verticesLength).limit(verticesLength + elementsLength)

	fun vertex(matrix4f: Matrix4f, x: Float, y: Float, z: Float) {
		grow((4 * 4).also { verticesLength += it })
		val vector4f = matrix4f.transform(Vector4f(x, y, z, 1.0F))
		buffer.putFloat(vector4f.x).putFloat(vector4f.y).putFloat(vector4f.z).putFloat(1.0F)
	}

	fun triangulate() {
		val size = 6 * 4
		grow(size * quads)
		var last = 0

		for (i in 0..<quads) {
			elementsLength += size
			buffer
				.putInt(0 + last)
				.putInt(1 + last)
				.putInt(2 + last)
				.putInt(2 + last)
				.putInt(3 + last)
				.putInt(0 + last)
			last += 4
		}
		buffer.flip()
	}

	fun free() {
		memFree(buffer)
	}

	private fun grow(size: Int) {
		buffer.takeIf { it.remaining() < size }?.let { buffer = memRealloc(it, it.capacity() + size) }
	}

	companion object {
		private const val INITIAL_LENGTH = 16

		inline fun use(size: Int, unit: MeshBuilder.() -> Unit) {
			MeshBuilder(size).also(unit).also(MeshBuilder::free)
		}
	}
}