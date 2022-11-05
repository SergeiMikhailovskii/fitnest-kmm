package com.fitnest.android.style

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

internal object LightColors {
    private val PrimaryColor = Color(0xFF92A3FD)
    private val OnPrimaryColor = Color(0xFFFFFFFF)
    private val PrimaryContainerColor = Color(0xFFDEE1FF)
    private val OnPrimaryContainerColor = Color(0xFF001258)
    private val SecondaryColor = Color(0xFF5A5D72)
    private val OnSecondaryColor = Color(0xFFFFFFFF)
    private val SecondaryContainerColor = Color(0xFFFFFFFF)
    private val OnSecondaryContainerColor = Color(0xFF92A3FD)
    private val TertiaryColor = Color(0xFFC58BF2)
    private val OnTertiaryColor = Color(0xFFFFFFFF)
    private val TertiaryContainerColor = Color(0xFFFFD7F2)
    private val OnTertiaryContainerColor = Color(0xFF2D1228)
    private val ErrorColor = Color(0xFFB00020)
    private val OnErrorColor = Color(0xFFFFFFFF)
    private val ErrorContainerColor = Color(0xFFFFDAF6)
    private val OnErrorContainerColor = Color(0xFF410002)
    private val BackgroundColor = Color(0xFFFEFBFF)
    private val OnBackgroundColor = Color(0xFF1D1617)
    private val SurfaceColor = Color(0xFFFEFBFF)
    private val OnSurfaceColor = Color(0xFF1B1B1F)
    private val OutlineColor = Color(0xFF767680)
    private val SurfaceVariantColor = Color(0xFFF7F8F8)
    private val OnSurfaceVariantColor = Color(0xFF7B6F72)
    private val InverseOnSurfaceColor = Color(0xFFFFFFFF)
    private val InversePrimaryColor = Color(0xFFB9C3FF)
    private val InverseSurfaceColor = Color(0xFF303034)
    private val SurfaceTintColor = Color(0xFF4758AC)

    fun getColorScheme() = ColorScheme(
        primary = PrimaryColor,
        onPrimary = OnPrimaryColor,
        primaryContainer = PrimaryContainerColor,
        onPrimaryContainer = OnPrimaryContainerColor,
        secondary = SecondaryColor,
        onSecondary = OnSecondaryColor,
        secondaryContainer = SecondaryContainerColor,
        onSecondaryContainer = OnSecondaryContainerColor,
        tertiary = TertiaryColor,
        onTertiary = OnTertiaryColor,
        tertiaryContainer = TertiaryContainerColor,
        onTertiaryContainer = OnTertiaryContainerColor,
        error = ErrorColor,
        onError = OnErrorColor,
        errorContainer = ErrorContainerColor,
        onErrorContainer = OnErrorContainerColor,
        background = BackgroundColor,
        onBackground = OnBackgroundColor,
        surface = SurfaceColor,
        onSurface = OnSurfaceColor,
        outline = OutlineColor,
        surfaceVariant = SurfaceVariantColor,
        onSurfaceVariant = OnSurfaceVariantColor,
        inverseOnSurface = InverseOnSurfaceColor,
        inversePrimary = InversePrimaryColor,
        inverseSurface = InverseSurfaceColor,
        outlineVariant = OutlineColor,
        scrim = OnTertiaryColor,
        surfaceTint = SurfaceTintColor
    )
}

internal object DarkColors {
    private val PrimaryColor = Color(0xFFB9C3FF)
    private val OnPrimaryColor = Color(0xFF12277B)
    private val PrimaryContainerColor = Color(0xFF2D3F93)
    private val OnPrimaryContainerColor = Color(0xFFDEE1FF)
    private val SecondaryColor = Color(0xFFC3C5DD)
    private val OnSecondaryColor = Color(0xFF2C2F42)
    private val SecondaryContainerColor = Color(0xFF434659)
    private val OnSecondaryContainerColor = Color(0xFFDFE1F9)
    private val TertiaryColor = Color(0xFFE5BAD8)
    private val OnTertiaryColor = Color(0xFF44263E)
    private val TertiaryContainerColor = Color(0xFF5D3C55)
    private val OnTertiaryContainerColor = Color(0xFFFFD7F2)
    private val ErrorColor = Color(0xFFFFB4AB)
    private val OnErrorColor = Color(0xFF690005)
    private val ErrorContainerColor = Color(0xFF93000A)
    private val OnErrorContainerColor = Color(0xFFFFDAD6)
    private val BackgroundColor = Color(0xFF1B1B1F)
    private val OnBackgroundColor = Color(0xFFE4E1E6)
    private val SurfaceColor = Color(0xFF1B1B1F)
    private val OnSurfaceColor = Color(0xFFE4E1E6)
    private val OutlineColor = Color(0xFF90909A)
    private val SurfaceVariantColor = Color(0xFF46464F)
    private val OnSurfaceVariantColor = Color(0xFFC6C5D0)
    private val InverseOnSurfaceColor = Color(0xFFC6C5D0)
    private val InversePrimaryColor = Color(0xFF4758AC)
    private val InverseSurfaceColor = Color(0xFFE4E1E6)
    private val SurfaceTintColor = Color(0xFFB9C3FF)

    fun getColorScheme() = ColorScheme(
        primary = PrimaryColor,
        onPrimary = OnPrimaryColor,
        primaryContainer = PrimaryContainerColor,
        onPrimaryContainer = OnPrimaryContainerColor,
        secondary = SecondaryColor,
        onSecondary = OnSecondaryColor,
        secondaryContainer = SecondaryContainerColor,
        onSecondaryContainer = OnSecondaryContainerColor,
        tertiary = TertiaryColor,
        onTertiary = OnTertiaryColor,
        tertiaryContainer = TertiaryContainerColor,
        onTertiaryContainer = OnTertiaryContainerColor,
        error = ErrorColor,
        onError = OnErrorColor,
        errorContainer = ErrorContainerColor,
        onErrorContainer = OnErrorContainerColor,
        background = BackgroundColor,
        onBackground = OnBackgroundColor,
        surface = SurfaceColor,
        onSurface = OnSurfaceColor,
        outline = OutlineColor,
        surfaceVariant = SurfaceVariantColor,
        onSurfaceVariant = OnSurfaceVariantColor,
        inverseOnSurface = InverseOnSurfaceColor,
        inversePrimary = InversePrimaryColor,
        inverseSurface = InverseSurfaceColor,
        outlineVariant = OutlineColor,
        scrim = OnTertiaryColor,
        surfaceTint = SurfaceTintColor
    )
}