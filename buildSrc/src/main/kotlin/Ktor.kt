object Ktor {
    object Common : Dependencies {
        private object Versions {
            const val KTOR_VERSION = "1.6.3"
            const val LOGBACK_VERSION = "1.6.3"
        }

        private const val KTOR_CORE = "io.ktor:ktor-client-core:${Versions.KTOR_VERSION}"
        private const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR_VERSION}"
        private const val KTOR_SERIALIZATION =
            "io.ktor:ktor-client-serialization:${Versions.KTOR_VERSION}"
        private const val LOGBACK = "ch.qos.logback:logback-classic:${Versions.LOGBACK_VERSION}"

        override val dependencies = mutableListOf(
            KTOR_CORE,
            KTOR_LOGGING,
            KTOR_SERIALIZATION,
            LOGBACK
        )
    }
}