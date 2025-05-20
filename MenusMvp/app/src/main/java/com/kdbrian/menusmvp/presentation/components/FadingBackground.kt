package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme

@Composable
fun FadingBackground(
    modifier: Modifier = Modifier,
    opacity: Float = 0.75f,
    colors: List<Color> = listOf(Color.Transparent, Color.LightGray),

    ) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors)
            )
            .alpha(opacity)
    )
}

@Preview
@Composable
private fun FadingBackgroundPrev() {
    MenusMvpTheme {
        FadingBackground()
    }
}