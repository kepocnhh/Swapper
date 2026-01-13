import com.android.build.api.variant.ApplicationVariant
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    google()
    mavenCentral()
}

plugins {
    kotlin("android")
    id("com.android.application")
    id("org.jetbrains.compose") version "1.9.3"
    id("org.jetbrains.kotlin.plugin.compose") version Version.kotlin
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
            sourceSets.getByName(name) {
                kotlin.srcDirs("../shared/$name/kotlin")
            }
        }
    }

    productFlavors {
        mapOf(
            "specifics" to setOf("mock"),
        ).forEach { (dimension, names) ->
            flavorDimensions += dimension
            names.forEach { name ->
                create(name) {
                    this.dimension = dimension
                    applicationIdSuffix = ".$name"
                    versionNameSuffix = "-$name"
                }
                sourceSets.getByName(name) {
                    kotlin.srcDirs("../shared/$name/kotlin")
                }
            }
        }
        create("real") {
            this.dimension = "specifics"
            sourceSets.getByName(name) {
                kotlin.srcDirs("../shared/$name/kotlin")
            }
        }
    }

    sourceSets.getByName("main") {
        kotlin.srcDirs("../shared/$name/kotlin")
    }
}

fun afterEvaluate(variant: ApplicationVariant) {
    val supported = setOf(
        "mockDebug",
        "realDebug",
    )
    if (!supported.contains(variant.name)) {
        tasks.getByName("pre${variant.name.replaceFirstChar(Char::titlecase)}Build") {
            doFirst {
                error("Variant \"${variant.name}\" is not supported!")
            }
        }
        return
    }
    tasks.getByName<JavaCompile>("compile${variant.name.replaceFirstChar(Char::titlecase)}JavaWithJavac") {
        targetCompatibility = Version.jvmTarget
    }
    tasks.getByName<KotlinCompile>("compile${variant.name.replaceFirstChar(Char::titlecase)}Kotlin") {
        compilerOptions.jvmTarget = JvmTarget.fromTarget(Version.jvmTarget)
    }
}

androidComponents.onVariants { variant ->
    val output = variant.outputs.single()
    check(output is com.android.build.api.variant.impl.VariantOutputImpl)
    output.outputFileName = listOf(
        rootProject.name,
        android.defaultConfig.versionName!!,
        variant.name,
        android.defaultConfig.versionCode!!.toString(),
    ).joinToString(separator = "-", postfix = ".apk")
    afterEvaluate {
        afterEvaluate(variant = variant)
    }
}

dependencies {
    implementation(compose.foundation)
    implementation("androidx.activity:activity-compose:1.12.2")
}
