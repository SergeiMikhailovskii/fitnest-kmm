object AndroidDependencies : Dependencies {

    private object Versions {
        const val MATERIAL_VERSION = "1.7.0"
        const val APPCOMPAT_VERSION = "1.3.1"
        const val CONSTRAINT_LAYOUT_VERSION = "2.1.1"
        const val PLAY_SERVICES_AUTH_VERSION = "19.2.0"
        const val FACEBOOK_VERSION = "14.1.0"
    }

    private const val MATERIAL =
        "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    private const val APPCOMPAT =
        "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    private const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    private const val ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
    private const val PLAY_SERVICES_AUTH =
        "com.google.android.gms:play-services-auth:${Versions.PLAY_SERVICES_AUTH_VERSION}"
    private const val FACEBOOK =
        "com.facebook.android:facebook-android-sdk:${Versions.FACEBOOK_VERSION}"

    override val dependencies = mutableListOf(
        MATERIAL,
        APPCOMPAT,
        CONSTRAINT_LAYOUT,
        ANALYTICS,
        PLAY_SERVICES_AUTH,
        FACEBOOK
    )

}