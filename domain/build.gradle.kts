

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
//    id("org.kodein.mock.mockmp") version "1.14.0"
    kotlin("plugin.serialization") version "1.9.20"
    id("com.android.library")
}

version = libs.versions.domain.get()

kotlin {
    androidTarget()
    ios()
    iosSimulatorArm64()

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
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
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
    }
    namespace = "com.fitnest.domain"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

// mockmp {
//    usesHelper = true
// }
