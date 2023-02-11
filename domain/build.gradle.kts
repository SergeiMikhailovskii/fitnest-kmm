import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("org.kodein.mock.mockmp") version "1.11.0"
    kotlin("plugin.serialization") version "1.8.10"
    id("com.android.library")
}

version = libs.versions.domain.get()

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
        ios.deploymentTarget = libs.versions.deploymentTarget.get()
        framework {
            baseName = "domain"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.bundles.domain)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.domainTest)
                implementation(kotlin("test"))
            }
        }
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
    compileSdk = libs.versions.targetSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
    namespace = "com.idfinance.domain"
}

mockmp {
    usesHelper = true
}
