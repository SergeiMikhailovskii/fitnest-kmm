plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01")
    implementation("androidx.compose.runtime:runtime:1.1.0-beta02")
    implementation("androidx.compose.ui:ui:1.1.0-beta02")
    implementation("androidx.compose.foundation:foundation:1.1.0-beta02")
    implementation("androidx.compose.foundation:foundation-layout:1.1.0-beta02")
    implementation("androidx.compose.material:material:1.1.0-beta02")
    implementation("androidx.navigation:navigation-compose:2.4.0-beta02")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-beta02")
    implementation("androidx.compose.ui:ui-tooling:1.1.0-beta02")
    implementation("androidx.compose.compiler:compiler:1.1.0-beta02")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.google.android.material:compose-theme-adapter:1.0.5")
    implementation("androidx.compose.material:material-icons-extended:1.1.0-beta02")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.21.0-beta")
    implementation("org.kodein.di:kodein-di-framework-android-x:7.8.0")
    implementation("org.kodein.di:kodein-di-framework-compose:7.6.0")
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