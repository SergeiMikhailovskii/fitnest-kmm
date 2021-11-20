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
                api(KotlinDependencies.SERIALIZATION)
                api(KotlinDependencies.SERIALIZATION_JSON)
                api(KotlinDependencies.COROUTINES)
                api(KodeinDependencies.Common.KODEIN_COMMON)
            }
        }
    }
}