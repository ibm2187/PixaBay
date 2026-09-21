package dev.ibm2187.pixabay.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable


@Composable
fun PixabayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        ColorSchemes.DarkColors
    } else {
        ColorSchemes.LightColors
    }

    CompositionLocalProvider(LocalDimens provides Dimens()) {
        MaterialTheme(colors) {
            content()
        }
    }
}

object PixabayTheme {
    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}
