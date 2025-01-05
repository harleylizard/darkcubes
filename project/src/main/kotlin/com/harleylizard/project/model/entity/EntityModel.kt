package com.harleylizard.project.model.entity

import com.harleylizard.project.model.MeshBuilder
import com.harleylizard.project.model.Shape
import org.joml.Matrix4f

class EntityModel(private val bones: Map<String, Bone>) : Shape {

	override fun build(matrix4f: Matrix4f, builder: MeshBuilder) {
	}
}