package com.harleylizard.project.input

import it.unimi.dsi.fastutil.ints.Int2BooleanArrayMap

class Keys {
	private val map = Int2BooleanArrayMap()

	fun set(key: Int, b: Boolean) {
		map[key] = b
	}

	fun isHeld(key: Int) = map[key]
}