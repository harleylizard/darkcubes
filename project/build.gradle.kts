import org.lwjgl.Lwjgl
import org.lwjgl.lwjgl

plugins {
    id("java-library")
    id("org.lwjgl.plugin") version "0.0.35"
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

dependencies {
    lwjgl {
        implementation(Lwjgl.Module.glfw, Lwjgl.Module.opengl, Lwjgl.Module.stb)
        implementation(Lwjgl.Addons.`joml 1․10․7`)
    }
    implementation("it.unimi.dsi:fastutil:8.5.15")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.google.guava:guava:33.3.1-jre")
}