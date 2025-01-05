package com.harleylizard.project.gl

import com.harleylizard.project.Resources.readLines
import org.lwjgl.opengl.GL45.*
import java.io.IOException

class ProgramPipeline private constructor(private val pipeline: Int) {

	fun bind() {
		glBindProgramPipeline(pipeline)
	}

	class Builder {
		private val pipeline = glCreateProgramPipelines()

		val build; get() = ProgramPipeline(pipeline.also { glValidateProgramPipeline(it) })

		@Throws(IOException::class)
		fun use(shader: Shader, path: String) {
			glUseProgramStages(pipeline, shader.bit, glCreateShaderProgramv(shader.type, path.readLines))
		}
	}

	companion object {

		inline fun build(unit: Builder.() -> Unit) = Builder().also(unit).build

		fun unbind() {
			glBindProgramPipeline(0)
		}
	}
}