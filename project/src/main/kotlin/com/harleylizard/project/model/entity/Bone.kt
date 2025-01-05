package com.harleylizard.project.model.entity

import com.harleylizard.project.model.MeshBuilder
import com.harleylizard.project.model.Shape
import org.joml.Matrix4f

class Bone(private val cubes: List<Cube>) : Shape {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder) {
	}
}