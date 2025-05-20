package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes
import kotlin.random.Random

@Composable
fun SimpleDisplayCardWithBanner(
    modifier: Modifier = Modifier,
    showBanner: Boolean,
    bannerColor: Color = Color.Red,
    bannerText: String = LoremIpsum(2).values.joinToString(),
    bannerTextColor: Color = Color.White,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), shape = Shapes.rounded12Dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (showBanner) 16.dp else 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = LoremIpsum(Random.nextInt(2, 4)).values.joinToString(),
                modifier = Modifier
                    .fillMaxWidth(1f),
            )


            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null
            )
        }


    }

}


@Preview
@Composable
private fun SimpleDisplayCardWithBannerPrev() {
    MenusMvpTheme {
        SimpleDisplayCardWithBanner(
            showBanner = true
        )
    }
}