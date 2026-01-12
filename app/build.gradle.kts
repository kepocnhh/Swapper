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
        getByName("desktopMain") {
            dependsOn(getByName("sharedMain"))
            kotlin.srcDirs("src/desktop/main/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "org.kepocnhh.swapper" // todo common namespace
    compileSdk = Version.Android.compileSdk

    defaultConfig {
        applicationId = namespace
        minSdk = Version.Android.minSdk
        targetSdk = Version.Android.targetSdk
        versionCode = 1
        versionName = "0.0.$versionCode"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.kepocnhh.swapper.AppKt" // todo common namespace
        nativeDistributions.packageName = rootProject.name
    }
}
