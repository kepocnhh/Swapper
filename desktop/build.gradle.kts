import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    google()
    mavenCentral()
}

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.9.3"
    id("org.jetbrains.kotlin.plugin.compose") version Version.kotlin
}

val flavors: Map<String, String> = mapOf(
    "buildType" to "debug",
    "specifics" to "mock",
    "platform" to "macos",
    "arch" to "arm64",
).mapValues { (dimension, value) ->
    properties[dimension]?.toString() ?: value
}

sourceSets {
    getByName("main") {
        kotlin.srcDirs("../shared/$name/kotlin")
        flavors.forEach { (_, name) ->
            kotlin.srcDirs("src/$name/kotlin")
            kotlin.srcDirs("../shared/$name/kotlin")
        }
    }
}

tasks.getByName<JavaCompile>("compileJava") {
    targetCompatibility = Version.jvmTarget
}

tasks.getByName<KotlinCompile>("compileKotlin") {
    compilerOptions.jvmTarget = JvmTarget.fromTarget(Version.jvmTarget)
}

fun getTargetFormat(): TargetFormat {
    return when (val platform = flavors["platform"]) {
        "macos" -> TargetFormat.Dmg
        else -> error("Platform \"$platform\" is not supported!")
    }
}

version = "1.0.0"

compose.desktop {
    application {
        mainClass = "org.kepocnhh.swapper.AppKt" // todo
        var packageName = rootProject.name
        val buildType = flavors["buildType"]
        if (buildType != "release") {
            packageName += "-$buildType"
        }
        val specifics = flavors["specifics"]
        if (specifics != "real") {
            packageName += "-$specifics"
        }
        nativeDistributions {
            this.packageName = packageName
            this.packageVersion = version as String
            targetFormats = setOf(getTargetFormat())
        }
    }
}

dependencies {
    when (val entry = Pair(flavors["platform"], flavors["arch"])) {
        "macos" to "arm64" -> {
            implementation(compose.desktop.macos_arm64)
        }
        else -> {
            val (platform, arch) = entry
            error("Platform \"$platform($arch)\" is not supported!")
        }
    }
}
