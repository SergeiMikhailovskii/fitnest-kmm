import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
}

dependencies {
    implementation(projects.data)
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.kodeinAndroid)
    implementation(platform(libs.firebaseBom))
    implementation(platform(libs.composeBom))
    androidTestImplementation(libs.bundles.androidInstrumentedTest)
    testImplementation(libs.bundles.androidTest)
    debugImplementation(libs.uiTestManifest)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.fitnest.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"${googleClientId}\"")
        resValue("string", "facebook_app_id", facebookAppId)
        resValue("string", "facebook_client_token", facebookClientToken)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerExtension.get()
    }
    namespace = "com.fitnest.android"
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/INDEX.LIST")
            add("META-INF/LICENSE.md")
            add("META-INF/LICENSE-notice.md")
        }
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
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
