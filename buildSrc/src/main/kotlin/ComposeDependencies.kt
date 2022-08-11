object ComposeDependencies : Dependencies {

    private object Versions {
        const val COMPOSE_ACCOMPANIST = "0.21.0-beta"
        const val COMPOSE_ACTIVITY_VERSION = "1.4.0"
        const val COMPOSE_CONSTRAINT_VERSION = "1.0.0-rc01"
        const val COMPOSE_NAVIGATION_VERSION = "2.4.0-beta02"
        const val COMPOSE_THEME_ADAPTER_VERSION = "1.0.5"
        const val COMPOSE_VERSION = "1.1.1"
        const val CHARTS_VERSION = "0.1.2"
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
    private const val COMPOSE_UTIL =
        "androidx.compose.ui:ui-util:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_ACTIVITY =
        "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY_VERSION}"
    private const val COMPOSE_THEME_ADAPTER =
        "com.google.android.material:compose-theme-adapter:${Versions.COMPOSE_THEME_ADAPTER_VERSION}"
    private const val COMPOSE_MATERIAL_ICONS =
        "androidx.compose.material:material-icons-extended:${Versions.COMPOSE_VERSION}"
    private const val COMPOSE_ACCOMPANIST_NAVIGATION =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.COMPOSE_ACCOMPANIST}"
    private const val COMPOSE_ACCOMPANIST_INSETS =
        "com.google.accompanist:accompanist-insets:${Versions.COMPOSE_ACCOMPANIST}"
    private const val COMPOSE_ACCOMPANIST_PAGER =
        "com.google.accompanist:accompanist-pager:${Versions.COMPOSE_ACCOMPANIST}"
    private const val COMPOSE_ACCOMPANIST_PAGER_INDICATORS =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.COMPOSE_ACCOMPANIST}"
    private const val CHARTS =
        "io.github.bytebeats:compose-charts:${Versions.CHARTS_VERSION}"

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
        COMPOSE_UTIL,
        COMPOSE_ACCOMPANIST_NAVIGATION,
        COMPOSE_ACCOMPANIST_INSETS,
        COMPOSE_ACCOMPANIST_PAGER,
        COMPOSE_ACCOMPANIST_PAGER_INDICATORS,
        CHARTS
    )

}