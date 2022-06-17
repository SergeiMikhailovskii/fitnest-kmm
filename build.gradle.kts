buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
            content {
                includeGroup("org.kodein")
            }
        }
        maven(url = "https://jitpack.io")
        maven(url = "https://dl.bintray.com/kodein-framework/Kodein-DI")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}