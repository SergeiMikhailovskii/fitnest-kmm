object ComposeDependencies : Dependencies {

    private object Versions {
        const val COMPOSE_ACCOMPANIST = "0.21.0-beta"
        const val COMPOSE_ACTIVITY_VERSION = "1.4.0"
        const val COMPOSE_CONSTRAINT_VERSION = "1.0.0-rc01"
        const val COMPOSE_NAVIGATION_VERSION = "2.4.0-beta02"
        const val COMPOSE_THEME_ADAPTER_VERSION = "1.0.5"
        const val COMPOSE_VERSION = "1.1.0-beta02"
    }

    private const val CONSTRAINT_COMPOSE =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE_CONSTRAINT_VERSION}"
    private const val COMPOSE_RUNTIME =
        "androidx.compose.runtime:runtime:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_FOUNDATION =
        "androidx.compose.foundation:foundation:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_FOUNDATION_LAYOUT =
        "androidx.compose.foundation:foundation-layout:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_FOUNDATION_MATERIAL =
        "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_NAVIGATION =
        "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION_VERSION}"
    private const val COMPOSE_RUNTIME_LIVEDATA =
        "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_UI_TOOLING =
        "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_COMPILER =
        "androidx.compose.compiler:compiler:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_ACTIVITY =
        "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY_VERSION}"
    private const val COMPOSE_THEME_ADAPTER =
        "com.google.android.material:compose-theme-adapter:${Versions.COMPOSE_THEME_ADAPTER_VERSION}"
    private const val COMPOSE_MATERIAL_ICONS =
        "androidx.compose.material:material-icons-extended:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_ACCOMPANIST =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.COMPOSE_ACCOMPANIST}"

    override val dependencies = mutableListOf(
        CONSTRAINT_COMPOSE,
        COMPOSE_RUNTIME,
        COMPOSE_UI,
        COMPOSE_FOUNDATION,
        COMPOSE_FOUNDATION_LAYOUT,
        COMPOSE_FOUNDATION_MATERIAL,
        COMPOSE_NAVIGATION,
        COMPOSE_RUNTIME_LIVEDATA,
        COMPOSE_UI_TOOLING,
        COMPOSE_COMPILER,
        COMPOSE_ACTIVITY,
        COMPOSE_THEME_ADAPTER,
        COMPOSE_MATERIAL_ICONS,
        COMPOSE_ACCOMPANIST,
    )

}