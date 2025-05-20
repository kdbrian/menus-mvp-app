package com.kdbrian.menusmvp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme

@Composable
fun RowWithIconAndText(
    modifier: Modifier = Modifier,
    text: AnnotatedString = buildAnnotatedString {
        withStyle(SpanStyle()) {
            append(LoremIpsum(3).values.joinToString())
        }
    },
    spacing: Dp,
    iconTint: Color = Color.Black,
    textColor: Color = Color.White,
    @DrawableRes icon: Int?,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        icon?.let {
            Icon(
                painter = painterResource(icon),
                contentDescription = text.text,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelSmall
        )
    }

}

@Preview
@Composable
private fun RowWithIconAndTextPrev() {
    MenusMvpTheme {
        RowWithIconAndText(
            spacing = 4.dp,
            icon = R.drawable.restaurant,
        )
    }
}