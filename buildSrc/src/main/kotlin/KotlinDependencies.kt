object KotlinDependencies : Dependencies {

    private object Versions {
        const val SERIALIZATION_VERSION = "1.2.2"
        const val COROUTINES_VERSION = "1.5.2"
    }

    const val SERIALIZATION =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.SERIALIZATION_VERSION}"
    const val SERIALIZATION_JSON =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION_VERSION}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"

    override val dependencies = mutableListOf(
        SERIALIZATION,
        COROUTINES
    )
}