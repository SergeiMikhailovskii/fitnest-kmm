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
    compileSdk = 31
    defaultConfig {
        applicationId = "com.fitnest.android"
        minSdk = 21
        targetSdk = 31
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta09"
    }
}