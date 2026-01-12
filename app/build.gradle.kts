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
        create("sharedMain") {
            kotlin.srcDirs("src/shared/main/kotlin")
            dependencies {
                implementation(compose.foundation)
            }
        }
        getByName("androidMain") {
            dependsOn(getByName("sharedMain"))
            kotlin.srcDirs("src/android/main/kotlin")
            dependencies {
                implementation(compose.foundation)
            }
        }
    }
}

android {
    namespace = "org.kepocnhh.swapper"
    compileSdk = Version.Android.compileSdk
}
