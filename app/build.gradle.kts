repositories {
    google()
    mavenCentral()
}

plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose") version "1.9.3"
    id("org.jetbrains.kotlin.plugin.compose") version Version.kotlin
}

kotlin {
    androidTarget()
    jvm("desktop")

    sourceSets {
        create("appMain") {
            dependencies {
                implementation(compose.foundation)
            }
        }
        getByName("androidMain") {
            dependsOn(getByName("appMain"))
            kotlin.srcDirs("src/android/main/kotlin")
            dependencies {
                implementation(compose.foundation)
            }
        }
        getByName("desktopMain") {
            dependsOn(getByName("appMain"))
            kotlin.srcDirs("src/desktop/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "org.kepocnhh.swapper"
    compileSdk = Version.Android.compileSdk
}
