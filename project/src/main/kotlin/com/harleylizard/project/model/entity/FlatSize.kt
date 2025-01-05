package com.harleylizard.project.model.entity

class FlatSize(private val width: Int, private val height: Int) {
	private inline val Cube.asFace; get() = Face(
		((toX - fromX) * 16.0F) / width,
		((toZ - fromZ) * 16.0F) / width,
		((toY - fromY) * 16.0F) / height,
		((toZ - fromZ) * 16.0F) / height
	)

	fun northFace(cube: Cube) = cube.asFace.let { (min0, max0, min1, max1) ->
		Face(max0, max1, max0 + min0, max1 + min1)
	}

	fun southFace(cube: Cube) = cube.asFace.let { (min0, max0, min1, max1) ->
		Face(max0 + min0 + max0, max1, max0 + min0 + max0 + min0, max1 + min1)
	}

	fun eastFace(cube: Cube) = cube.asFace.let { (min0, max0, min1, max1) ->
		Face(0.0F, max1, max0, max1 + min1)
	}

	fun westFace(cube: Cube) = cube.asFace.let { (min0, max0, min1, max1) ->
		Face(max0 + min0, max1, max0 + min0 + max0, max1 + min1)
	}

	fun topFace(cube: Cube) = cube.asFace.let { (min0, max0, min1, max1) ->
		Face(max0, 0.0F, max0 + min0, max1)
	}

	fun bottomFace(cube: Cube) = cube.asFace.let { (min0, max0, min1, max1) ->
		Face(max0 + min0, 0.0F, min0 + max0 + min0, max1)
	}

	fun move(face: Face, x: Int, y: Int): Face {
		val j = x.toFloat() / width
		val k = y.toFloat() / height
		return face.move(j, k)
	}
}