plugins {
    id("fabric-loom") version "1.9-SNAPSHOT" apply false
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}
