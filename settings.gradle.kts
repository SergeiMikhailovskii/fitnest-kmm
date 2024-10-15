pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        id("org.jetbrains.compose").version("1.7.0")
    }
}

rootProject.name = "fitnest"
include(":androidApp", ":data", ":domain", ":presentation", ":accompanistMultiplatform")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
