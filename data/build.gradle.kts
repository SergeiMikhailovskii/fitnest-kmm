plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
//    id("org.kodein.mock.mockmp") version "1.14.0"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = libs.versions.data.get()

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    namespace = "com.fitnest"
//    configurations {
//        create("androidTestApi")
//        create("androidTestDebugApi")
//        create("androidTestReleaseApi")
//        create("testApi")
//        create("testDebugApi")
//        create("testReleaseApi")
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    androidTarget()
    ios()
    iosArm64()
    iosX64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = libs.versions.deploymentTarget.get()
        framework {
            baseName = "data"
        }
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.domain)
                implementation(libs.bundles.ktorCommon)
                implementation(libs.bundles.dataCommon)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.dataTest)
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.bundles.dataAndroid)
                api(libs.work)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.bundles.dataAndroidTest)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.bundles.dataIOS)
            }
        }
        val iosTest by getting
    }
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

sqldelight {
    database("FitnestDatabase") {
        packageName = "com.fitnest"
    }
}

// mockmp {
//    usesHelper = true
// }
