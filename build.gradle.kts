import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.1.0"
}

repositories {
    mavenCentral()
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
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

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        filter {
            exclude {
                it.file.path.contains("build/generated")
            }
        }
    }

    tasks.withType(KotlinCompile::class).configureEach {
        kotlinOptions {
            if (project.hasProperty("fitnest.enableComposeCompilerReports")) {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        project.buildDir.absolutePath + "/compose_metrics"
                )
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        project.buildDir.absolutePath + "/compose_metrics"
                )
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
