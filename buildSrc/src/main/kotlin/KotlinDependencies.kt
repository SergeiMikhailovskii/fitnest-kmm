object KotlinDependencies : Dependencies {

    private object Versions {
        const val SERIALIZATION_VERSION = "1.2.2"
        const val COROUTINES_VERSION = "1.5.2"
        const val DATETIME_VERSION = "0.3.2"
    }

    const val SERIALIZATION =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.SERIALIZATION_VERSION}"
    const val SERIALIZATION_JSON =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION_VERSION}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    const val DATETIME = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.DATETIME_VERSION}"

    override val dependencies = mutableListOf(
        SERIALIZATION,
        COROUTINES,
        DATETIME,
    )
}