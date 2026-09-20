package dev.ibm2187.pixabay.design.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

object ColorSchemes {
    val LightColors = lightColorScheme(
        primary = Green40,
        onPrimary = White,
        primaryContainer = Green90,
        onPrimaryContainer = Green10,

        secondary = GreenGrey40,
        onSecondary = White,
        secondaryContainer = GreenGrey90,
        onSecondaryContainer = GreenGrey10,

        tertiary = Blue40,
        onTertiary = White,
        tertiaryContainer = Blue90,
        onTertiaryContainer = Blue10,

        error = Red40,
        onError = White,
        errorContainer = Red90,
        onErrorContainer = Red10,

        background = Neutral99,
        onBackground = Neutral10,
        surface = Neutral99,
        onSurface = Neutral10,
        surfaceVariant = NeutralVariant90,
        onSurfaceVariant = NeutralVariant30,
        outline = NeutralVariant50,
    )

    val DarkColors = darkColorScheme(
        primary = Green80,
        onPrimary = Green20,
        primaryContainer = Green30,
        onPrimaryContainer = Green90,

        secondary = GreenGrey80,
        onSecondary = GreenGrey20,
        secondaryContainer = GreenGrey30,
        onSecondaryContainer = GreenGrey90,

        tertiary = Blue80,
        onTertiary = Blue20,
        tertiaryContainer = Blue30,
        onTertiaryContainer = Blue90,

        error = Red80,
        onError = Red20,
        errorContainer = Red30,
        onErrorContainer = Red90,

        background = Neutral10,
        onBackground = Neutral90,
        surface = Neutral10,
        onSurface = Neutral90,
        surfaceVariant = NeutralVariant30,
        onSurfaceVariant = NeutralVariant80,
        outline = NeutralVariant60,
    )
}