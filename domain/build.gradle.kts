plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.30"
}

version = "1.0"

kotlin {
    jvm()
    ios()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.2")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
                api("org.kodein.di:kodein-di:7.8.0")
            }
        }
    }
}