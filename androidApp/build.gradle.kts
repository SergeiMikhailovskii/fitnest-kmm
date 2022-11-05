import java.io.FileInputStream
import java.util.Properties

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
    implementation(platform("com.google.firebase:firebase-bom:31.0.2"))
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.fitnest.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"${googleClientId}\"")
        resValue("string", "facebook_app_id", facebookAppId)
        resValue("string", "facebook_client_token", facebookClientToken)
    }
    signingConfigs {
        getByName("debug") {
            storeFile = file("build_system/debug.keystore")
            storePassword = debugKeystorePassword
            keyAlias = "debug"
            keyPassword = debugKeyPassword
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
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    namespace = "com.fitnest.android"
}

val properties: Properties
    get() {
        val propFile = rootProject.file("local.properties")
        val properties = Properties()
        properties.load(FileInputStream(propFile))
        return properties
    }

val googleClientId: String
    get() = properties.getProperty("GOOGLE_CLIENT_ID")

val debugKeystorePassword: String
    get() = properties.getProperty("DEBUG_KEYSTORE_PASSWORD")

val debugKeyPassword: String
    get() = properties.getProperty("DEBUG_KEY_PASSWORD")

val facebookAppId: String
    get() = properties.getProperty("facebook_app_id")

val facebookClientToken: String
    get() = properties.getProperty("facebook_client_token")
