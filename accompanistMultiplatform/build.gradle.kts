plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

version = libs.versions.accompanistMultiplatform.get()

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    applyDefaultHierarchyTemplate()

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
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
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
