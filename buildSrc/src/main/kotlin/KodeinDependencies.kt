object KodeinDependencies {

    private object Versions {
        const val KODEIN_VERSION = "7.15.1"
    }

    object Common : Dependencies {
        const val KODEIN_COMMON = "org.kodein.di:kodein-di:${Versions.KODEIN_VERSION}"

        override val dependencies = mutableListOf(
            KODEIN_COMMON,
        )
    }

}