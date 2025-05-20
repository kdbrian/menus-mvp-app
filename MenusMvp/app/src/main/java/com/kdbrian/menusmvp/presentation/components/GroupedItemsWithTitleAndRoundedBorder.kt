package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.util.Shapes

@Composable
fun GroupedItemsWithTitleAndRoundedBorder(
    modifier: Modifier = Modifier,
    title: AnnotatedString = buildAnnotatedString {
        withStyle(SpanStyle()) {
            append(LoremIpsum(3).values.joinToString())
        }
    },
    trailingIcon: @Composable () -> Unit = {},
    expandAction: () -> Unit = {},
    shape: Shape = Shapes.rounded32Dp,
    content: @Composable () -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(14.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = shape
            )
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(
                    1f
                )
            )

            trailingIcon()
        }

        content()
    }


}