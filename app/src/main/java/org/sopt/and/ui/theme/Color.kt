package org.sopt.and.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


// Gray Scale
val White = Color(0xFFFFFFFF)
val Gray01 = Color(0xFFF6F8F9)
val Gray02 = Color(0xFFF3F5F7)
val Gray03 = Color(0xFFDCDFE3)
val Gray04 = Color(0xFFD1D4D8)
val Gray05 = Color(0xFFADB3BA)
val Gray06 = Color(0xFF868C94)
val Gray07 = Color(0xFF5D626B)
val Gray08 = Color(0xFF454A54)
val Gray09 = Color(0xFF2D3036)
val Gray10 = Color(0xFF212327)
val Black = Color(0xFF121212)

// Key Color
val Green = Color(0xFF29FF74)

// Memento color scheme class
@Immutable
data class MementoColors(
    val white: Color,
    val gray01: Color,
    val gray02: Color,
    val gray03: Color,
    val gray04: Color,
    val gray05: Color,
    val gray06: Color,
    val gray07: Color,
    val gray08: Color,
    val gray09: Color,
    val gray10: Color,
    val black: Color,
    val green: Color,
)

// Default color scheme
val defaultMementoColors = MementoColors(
    white = White,
    gray01 = Gray01,
    gray02 = Gray02,
    gray03 = Gray03,
    gray04 = Gray04,
    gray05 = Gray05,
    gray06 = Gray06,
    gray07 = Gray07,
    gray08 = Gray08,
    gray09 = Gray09,
    gray10 = Gray10,
    black = Black,
    green = Green,
)

val LocalMementoColors =
    staticCompositionLocalOf {
        defaultMementoColors
    }