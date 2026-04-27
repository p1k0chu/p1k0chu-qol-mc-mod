plugins {
    id("net.fabricmc.fabric-loom-remap")
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")
}

tasks.processResources {
    val version = version
    val minecraftVersion = project.property("minecraft_version")!!
    val loaderVersion = project.property("loader_version")!!
    val fabricApiVersion = project.property("fabric_api_version")!!

    inputs.property("version", version)
    inputs.property("minecraft_version", minecraftVersion)
    inputs.property("loader_version", loaderVersion)
    inputs.property("fabric_api_version", fabricApiVersion)

    filesMatching("fabric.mod.json") {
        expand(
            "version" to version,
            "loader_version" to loaderVersion,
            "minecraft_version" to minecraftVersion,
            "fabric_api_version" to fabricApiVersion
        )
    }
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create("p1k0chu-qol-mc-mod") {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    targetCompatibility = JavaVersion.VERSION_21
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
}
