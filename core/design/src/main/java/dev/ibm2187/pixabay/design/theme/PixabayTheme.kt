package dev.ibm2187.pixabay.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


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

    MaterialTheme(colors) {
        content()
    }
}
