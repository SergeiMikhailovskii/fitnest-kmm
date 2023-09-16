import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

version = libs.versions.presentation.get()

kotlin {
    androidTarget()
    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Presentation Module"
        homepage = "Link to the Presentation Module homepage"
        ios.deploymentTarget = libs.versions.deploymentTarget.get()
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        framework {
            baseName = "presentation"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(ExperimentalComposeLibrary::class)
                api(compose.components.resources)
                implementation(libs.kodeinCompose)
                api(libs.resources.compose)
                api(projects.data)
                api(projects.accompanistMultiplatform)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.lifecycle.viewmodel.ktx)
                dependsOn(commonMain)
            }
        }
    }
}

android {
    namespace = "com.fitnest.presentation"
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
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

multiplatformResources {
    multiplatformResourcesPackage = "com.fitnest.presentation"
}
