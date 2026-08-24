package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LedgerBrassLight,
    onPrimary = LedgerInkNavyDark,
    primaryContainer = LedgerBrassContainer,
    onPrimaryContainer = LedgerBrassLight,
    secondary = LedgerSlateBlueLight,
    onSecondary = LedgerInkNavyDark,
    secondaryContainer = LedgerSlateBlueContainer,
    onSecondaryContainer = LedgerSlateBlueLight,
    tertiary = LedgerBrass,
    background = LedgerParchmentDark,
    onBackground = LedgerParchmentTextLight,
    surface = LedgerPaperDark,
    onSurface = LedgerParchmentTextLight,
    surfaceVariant = LedgerInkNavy,
    onSurfaceVariant = LedgerParchmentMutedLight,
    outline = LedgerBorderTanDark,
    outlineVariant = LedgerBorderTanDark.copy(alpha = 0.5f)
)

private val LightColorScheme = lightColorScheme(
    primary = LedgerBrass,
    onPrimary = Color.White,
    primaryContainer = LedgerBrassPale,
    onPrimaryContainer = Color(0xFF423013),
    secondary = LedgerSlateBlue,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE6EEF5),
    onSecondaryContainer = Color(0xFF1E3245),
    tertiary = LedgerInkNavy,
    background = LedgerParchment,
    onBackground = LedgerInkTextDark,
    surface = LedgerPaperLight,
    onSurface = LedgerInkTextDark,
    surfaceVariant = Color(0xFFEFE8DA),
    onSurfaceVariant = LedgerInkMutedDark,
    outline = LedgerBorderTanLight,
    outlineVariant = LedgerBorderTanLight.copy(alpha = 0.5f)
)

@Composable
fun LedgerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = LedgerTypography,
        content = content
    )
}
