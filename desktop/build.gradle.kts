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

tasks.getByName<JavaCompile>("compileJava") {
    targetCompatibility = Version.jvmTarget
}

tasks.getByName<KotlinCompile>("compileKotlin") {
    compilerOptions.jvmTarget = JvmTarget.fromTarget(Version.jvmTarget)
}

sourceSets {
    getByName("main") {
        kotlin.srcDirs("../shared/$name/kotlin")
    }
}

compose.desktop {
    application {
        mainClass = "org.kepocnhh.swapper.AppKt" // todo common namespace

        nativeDistributions {
            packageName = rootProject.name
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe)
        }
    }
}

dependencies {
    implementation(compose.desktop.currentOs)
}
