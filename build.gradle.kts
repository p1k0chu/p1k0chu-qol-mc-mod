import net.fabricmc.loom.api.LoomGradleExtensionAPI

val obfuscated = sc.current.parsed < "26.1"
plugins.apply(if(obfuscated) "net.fabricmc.fabric-loom-remap" else "net.fabricmc.fabric-loom")
val loom = the<LoomGradleExtensionAPI>()
val modImplementation = if(obfuscated) configurations.named("modImplementation") else configurations.implementation
val modJar = if(obfuscated) tasks.named<Zip>("remapJar") else tasks.named<Zip>("jar")

version = "${property("mod_version")}+${sc.current.version}"

repositories {
    mavenCentral()
}

dependencies {
    "minecraft"("com.mojang:minecraft:${sc.current.version}")
    modImplementation("net.fabricmc:fabric-loader:${project.property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("deps.fabric_api")}")

    if (obfuscated) {
        "mappings"(loom.officialMojangMappings())
    }
}

tasks.processResources {
    val version = version
    val minecraftVersion = project.property("fmj.minecraft")!!
    val loaderVersion = project.property("deps.fabric_loader")!!
    val fabricApiVersion = project.property("deps.fabric_api")!!
    val javaVersion = project.property("java_version")!!

    inputs.property("version", version)
    inputs.property("fmj.minecraft", minecraftVersion)
    inputs.property("deps.fabric_loader", loaderVersion)
    inputs.property("deps.fabric_api", fabricApiVersion)
    inputs.property("java_version", javaVersion)

    filesMatching("fabric.mod.json") {
        expand(
            "version" to version,
            "loader_version" to loaderVersion,
            "minecraft_version" to minecraftVersion,
            "fabric_api_version" to fabricApiVersion,
            "java_version" to javaVersion
        )
    }
}

extensions.configure<LoomGradleExtensionAPI>() {
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
