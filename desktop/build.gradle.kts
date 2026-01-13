import org.jetbrains.compose.desktop.application.dsl.TargetFormat

repositories {
    google()
    mavenCentral()
}

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version "1.9.3"
    id("org.jetbrains.kotlin.plugin.compose") version Version.kotlin
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
