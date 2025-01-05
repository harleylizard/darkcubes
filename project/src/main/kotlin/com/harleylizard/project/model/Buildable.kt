package com.harleylizard.project.model

import com.harleylizard.project.material.Material
import org.joml.Matrix4f

interface Buildable {

	fun build(matrix4f: Matrix4f, builder: MeshBuilder, material: Material)
}