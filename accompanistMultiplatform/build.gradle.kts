plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

version = libs.versions.accompanistMultiplatform.get()

kotlin {
    android()
    ios()
    iosArm64()
    iosX64()

    cocoapods {
        summary = "Some description for the Accompanist Multiplatform Module"
        homepage = "Link to the Accompanist Multiplatform Module homepage"
        ios.deploymentTarget = libs.versions.deploymentTarget.get()
        framework {
            baseName = "accompanistMultiplatform"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.accompanistPlaceholder)
            }
        }
    }
}

android {
    namespace = "com.fitnest.accompanistmultiplatform"
    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
