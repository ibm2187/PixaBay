package dev.ibm2187.pixabay.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val iconSize: Dp = 24.dp,
    val avatarSize: Dp = 32.dp,
    val cardPadding: Dp = 12.dp,
    val cardSpacing: Dp = 8.dp,
)

val LocalDimens = staticCompositionLocalOf { Dimens() }
