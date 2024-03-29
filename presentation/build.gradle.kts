import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
    id("kotlin-parcelize")
}

version = libs.versions.presentation.get()

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    applyDefaultHierarchyTemplate()

    cocoapods {
        summary = "Some description for the Presentation Module"
        homepage = "Link to the Presentation Module homepage"
        ios.deploymentTarget = libs.versions.deploymentTarget.get()
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
        framework {
            baseName = "presentation"
            export("com.arkivanov.decompose:decompose:${libs.versions.decompose.get()}")
            export("com.arkivanov.essenty:lifecycle:${libs.versions.essenty.get()}")
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.decompose)
                api(libs.essenty)
                implementation(libs.decomposeExtensionsComposeMultiplatform)
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
