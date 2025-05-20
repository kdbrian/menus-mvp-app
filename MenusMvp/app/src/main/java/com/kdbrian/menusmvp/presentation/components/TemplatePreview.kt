package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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

@Composable
fun TemplatePreview(modifier: Modifier = Modifier) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Shapes.rounded12Dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(shape = Shapes.rounded12Dp, color = Color.LightGray)
                    .padding(4.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = LoremIpsum(3).values.joinToString(),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = LoremIpsum(6).values.joinToString(),
                    style = MaterialTheme.typography.labelLarge
                )
            }

        }
    }


}

@Preview
@Composable
private fun TemplatePreviewPrev() {
    MenusMvpTheme {
        TemplatePreview()
    }
}