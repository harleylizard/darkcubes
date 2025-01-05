plugins {
    id("fabric-loom")
    id("org.jetbrains.kotlin.jvm")
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

loom {
    runs {
        getByName("client").apply {
            client()
            ideConfigGenerated(true)
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.16.9")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.114.0+1.21.1")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.13.0+kotlin.2.1.0")

    implementation(project(":project"))
}