object KotlinDependencies : Dependencies {

    private object Versions {
        const val SERIALIZATION_VERSION = "1.4.1"
        const val COROUTINES_VERSION = "1.6.4"
        const val DATETIME_VERSION = "0.4.0"
        const val IMMUTABLE_COLLECTIONS_VERSION = "0.3.5"
    }

    const val SERIALIZATION =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.SERIALIZATION_VERSION}"
    const val SERIALIZATION_JSON =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION_VERSION}"
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    const val DATETIME = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.DATETIME_VERSION}"
    const val IMMUTABLE_COLLECTIONS =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.IMMUTABLE_COLLECTIONS_VERSION}"

    override val dependencies = mutableListOf(
        SERIALIZATION,
        COROUTINES,
        DATETIME,
        IMMUTABLE_COLLECTIONS
    )
}