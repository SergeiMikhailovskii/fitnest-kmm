object AndroidDependencies : Dependencies {

    private object Versions {
        const val MATERIAL_VERSION = "1.4.0"
        const val APPCOMPAT_VERSION = "1.3.1"
        const val CONSTRAINT_LAYOUT_VERSION = "2.1.1"
    }

    private const val MATERIAL =
        "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    private const val APPCOMPAT =
        "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    private const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    private const val ANALYTICS = "com.google.firebase:firebase-analytics-ktx"

    override val dependencies = mutableListOf(
        MATERIAL,
        APPCOMPAT,
        CONSTRAINT_LAYOUT,
        ANALYTICS
    )

}