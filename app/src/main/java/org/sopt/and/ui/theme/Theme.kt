package org.sopt.and.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object MementoTheme {
    val colors: MementoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMementoColors.current
}

@Composable
fun ProvideMementoColorsAndTypography(
    colors: MementoColors,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalMementoColors provides colors,
        content = content,
    )
}

@Composable
fun MEMENTOTheme(content: @Composable () -> Unit) {
    ProvideMementoColorsAndTypography(
        colors = defaultMementoColors,

        ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    statusBarColor = White.toArgb()
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }
        MaterialTheme(content = content)
    }
}