object KodeinDependencies {

    private object Versions {
        const val KODEIN_ANDROID_VERSION = "7.8.0"
        const val KODEIN_COMPOSE_VERSION = "7.8.0"
    }

    object Android : Dependencies {
        private const val KODEIN_ANDROID =
            "org.kodein.di:kodein-di-framework-android-x:${Versions.KODEIN_ANDROID_VERSION}"
        private const val KODEIN_COMPOSE =
            "org.kodein.di:kodein-di-framework-compose:${Versions.KODEIN_COMPOSE_VERSION}"

        override val dependencies = mutableListOf(
            KODEIN_ANDROID,
            KODEIN_COMPOSE
        )
    }

}