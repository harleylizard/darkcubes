package com.harleylizard.project

import java.nio.file.Paths
import java.util.*

class Options private constructor(private val arguments: Map<String, String>) {

	fun getPath(name: String) = Paths.get(arguments[name])

	companion object {

		fun parse(args: Array<String>): Options {
			val map = mutableMapOf<String, String>()
			var i = 0
			while (i < args.size) {
				val key = args[i].let { it.substring(it.lastIndexOf("-") + 1) }
				map[key] = args[i + 1]
				i += 2
			}
			return Options(Collections.unmodifiableMap(map))
		}
	}
}