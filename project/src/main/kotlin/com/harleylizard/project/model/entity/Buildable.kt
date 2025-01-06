package com.harleylizard.project.model.entity

import com.harleylizard.project.model.MeshBuilder
import org.joml.Matrix4f

sealed interface Buildable {

	fun build(matrix4f: Matrix4f, builder: MeshBuilder, size: TextureSize)
}