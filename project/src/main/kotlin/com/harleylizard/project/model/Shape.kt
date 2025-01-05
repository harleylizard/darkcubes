package com.harleylizard.project.model

import org.joml.Matrix4f

interface Shape {

	fun build(matrix4f: Matrix4f, builder: MeshBuilder)
}