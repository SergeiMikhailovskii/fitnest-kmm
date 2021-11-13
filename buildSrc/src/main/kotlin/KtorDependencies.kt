import KtorDependencies.Versions.KTOR_VERSION

object KtorDependencies {

    object Versions {
        const val KTOR_VERSION = "1.6.3"
    }

    object Common : Dependencies {
        private object Versions {
            const val LOGBACK_VERSION = "1.2.6"
        }

        const val KTOR_CORE = "io.ktor:ktor-client-core:$KTOR_VERSION"
        const val KTOR_LOGGING = "io.ktor:ktor-client-logging:$KTOR_VERSION"
        const val KTOR_SERIALIZATION =
            "io.ktor:ktor-client-serialization:$KTOR_VERSION"
        const val LOGBACK = "ch.qos.logback:logback-classic:${Versions.LOGBACK_VERSION}"

        override val dependencies = mutableListOf(
            KTOR_CORE,
            KTOR_LOGGING,
            KTOR_SERIALIZATION,
            LOGBACK
        )
    }

    object Android : Dependencies {
        const val KTOR_ANDROID ="io.ktor:ktor-client-android:$KTOR_VERSION"

        override val dependencies = mutableListOf(
            KTOR_ANDROID,
        )
    }

    object IOS : Dependencies {
        const val KTOR_IOS = "io.ktor:ktor-client-ios:$KTOR_VERSION"

        override val dependencies = mutableListOf(
            KTOR_IOS
        )
    }
}