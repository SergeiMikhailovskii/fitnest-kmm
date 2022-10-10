import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.5.30"
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "domain"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(KotlinDependencies.SERIALIZATION)
                api(KotlinDependencies.SERIALIZATION_JSON)
                api(KotlinDependencies.COROUTINES)
                api(KotlinDependencies.DATETIME)
                api(KotlinDependencies.IMMUTABLE_COLLECTIONS)
                api(KodeinDependencies.Common.KODEIN_COMMON)
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosTest by getting {
            dependsOn(commonTest)
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    namespace = "com.idfinance.domain"
}
