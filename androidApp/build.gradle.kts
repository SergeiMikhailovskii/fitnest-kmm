plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation(AndroidDependencies.dependencies)
    implementation(ComposeDependencies.dependencies)
    implementation(KodeinDependencies.Android.dependencies)
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.mikhailovskii.kmmtest.android"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta09"
        kotlinCompilerVersion = "1.5.10"
    }
}