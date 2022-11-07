pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "fitnest"
include(":androidApp")
include(":data")
include(":domain")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
