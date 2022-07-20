import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
}

dependencies {
    implementation(project(":data"))
    implementation(AndroidDependencies.dependencies)
    implementation(ComposeDependencies.dependencies)
    implementation(KodeinDependencies.Android.dependencies)
    implementation(platform("com.google.firebase:firebase-bom:30.1.0"))
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.fitnest.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "GOOGLE_CLIENT_ID", getGoogleClientId())
    }
    signingConfigs {
        getByName("debug") {
            storeFile = file("build_system/debug.keystore")
            storePassword = getDebugKeystorePassword()
            keyAlias = "debug"
            keyPassword = getDebugKeyPassword()
        }
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

fun getGoogleClientId(): String {
    val propFile = rootProject.file("local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty("GOOGLE_CLIENT_ID")
}

fun getDebugKeystorePassword(): String {
    val propFile = rootProject.file("local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty("DEBUG_KEYSTORE_PASSWORD")
}

fun getDebugKeyPassword(): String {
    val propFile = rootProject.file("local.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties.getProperty("DEBUG_KEY_PASSWORD")
}