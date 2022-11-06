object ComposeDependencies : Dependencies {

    private object Versions {
        const val COMPOSE_ACCOMPANIST = "0.27.0"
        const val COMPOSE_ACTIVITY_VERSION = "1.7.0-alpha02"
        const val COMPOSE_CONSTRAINT_VERSION = "1.0.1"
        const val COMPOSE_NAVIGATION_VERSION = "2.5.3"
        const val COMPOSE_THEME_ADAPTER_VERSION = "1.0.21"
        const val COMPOSE_COMPILER_VERSION = "1.3.2"
    }

    private const val CONSTRAINT_COMPOSE =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSE_CONSTRAINT_VERSION}"
    private const val COMPOSE_NAVIGATION =
        "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION_VERSION}"
    private const val COMPOSE_COMPILER =
        "androidx.compose.compiler:compiler:${Versions.COMPOSE_COMPILER_VERSION}"
    private const val COMPOSE_ACTIVITY =
        "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY_VERSION}"
    private const val COMPOSE_THEME_ADAPTER =
        "com.google.android.material:compose-theme-adapter-3:${Versions.COMPOSE_THEME_ADAPTER_VERSION}"
    private const val COMPOSE_ACCOMPANIST_NAVIGATION =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.COMPOSE_ACCOMPANIST}"
    private const val COMPOSE_ACCOMPANIST_INSETS =
        "com.google.accompanist:accompanist-insets:${Versions.COMPOSE_ACCOMPANIST}"
    private const val COMPOSE_ACCOMPANIST_PAGER =
        "com.google.accompanist:accompanist-pager:${Versions.COMPOSE_ACCOMPANIST}"
    private const val COMPOSE_ACCOMPANIST_PAGER_INDICATORS =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.COMPOSE_ACCOMPANIST}"

    private const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime"
    private const val COMPOSE_UI = "androidx.compose.ui:ui"
    private const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation"
    private const val COMPOSE_FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout"
    private const val COMPOSE_RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata"
    private const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    private const val COMPOSE_UTIL = "androidx.compose.ui:ui-util"
    private const val COMPOSE_MATERIAL_ICONS = "androidx.compose.material:material-icons-extended"
    private const val COMPOSE_MATERIAL = "androidx.compose.material3:material3"



    override val dependencies = mutableListOf(
        CONSTRAINT_COMPOSE,
        COMPOSE_NAVIGATION,
        COMPOSE_COMPILER,
        COMPOSE_ACTIVITY,
        COMPOSE_THEME_ADAPTER,
        COMPOSE_ACCOMPANIST_NAVIGATION,
        COMPOSE_ACCOMPANIST_INSETS,
        COMPOSE_ACCOMPANIST_PAGER,
        COMPOSE_ACCOMPANIST_PAGER_INDICATORS,
        COMPOSE_RUNTIME,
        COMPOSE_UI,
        COMPOSE_FOUNDATION,
        COMPOSE_FOUNDATION_LAYOUT,
        COMPOSE_RUNTIME_LIVEDATA,
        COMPOSE_UI_TOOLING,
        COMPOSE_UTIL,
        COMPOSE_MATERIAL_ICONS,
        COMPOSE_MATERIAL
    )

}