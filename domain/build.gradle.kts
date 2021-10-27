plugins {
    kotlin("multiplatform")
}

version = "1.0"

kotlin {
    jvm()
    ios()
    sourceSets {
        val commonMain by getting
    }
}