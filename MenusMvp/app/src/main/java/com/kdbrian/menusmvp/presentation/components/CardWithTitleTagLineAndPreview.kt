package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.ui.theme.rubikmarkerhatchregular
import com.kdbrian.menusmvp.presentation.util.Shapes


@Composable
fun CardWithTitleTagLineAndPreview(
    previewUrl: String,
    onSelect : () -> Unit,
    title: String = LoremIpsum(2).values.joinToString(),
    tagline: String = LoremIpsum(6).values.joinToString(),
) {
    Card(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(4.dp),
        shape = Shapes.rounded24Dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            AsyncImage(
                model = previewUrl,
                error = painterResource(R.drawable.rih___rocky),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                contentDescription = title
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.LightGray
                            )
                        ),
                        alpha = 0.85f
                    )
            )

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 48.sp,
                    fontFamily = rubikmarkerhatchregular,
                    color = Color.White
                )

                Text(
                    text = tagline,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = rubikmarkerhatchregular,
                    color = Color.White
                )
            }


            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                color = Color.DarkGray,
                shape = Shapes.rounded24Dp
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_wine_bar_24),
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.padding(4.dp)
                )
            }

        }

    }
}