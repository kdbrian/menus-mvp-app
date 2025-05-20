package com.kdbrian.menusmvp.presentation.features.creators

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.components.TemplatePreview
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes

@Composable
fun Create(modifier: Modifier = Modifier) {


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {

        item {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontSize = 64.sp, fontWeight = FontWeight.SemiBold)) {
                        append("C")
                    }

                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("reativity")
                    }

                    withStyle(SpanStyle(fontSize = 64.sp, fontWeight = FontWeight.SemiBold)) {
                        append(" O")
                    }

                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("ptimized")
                    }
                },
                fontSize = 30.sp,
                modifier = Modifier.padding(12.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {


                Card(
                    modifier = Modifier
                        .fillMaxHeight(0.50f)
                        .fillMaxWidth(0.80f),
                    shape = Shapes.rounded24Dp,
                    content = {
                    }
                )

                Card(
                    modifier = Modifier
                        .fillMaxHeight(0.65f)
                        .fillMaxWidth(0.60f),
                    shape = Shapes.rounded24Dp,
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    content = {
                        Image(
                            painter = painterResource(R.drawable.rih___rocky),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )


                Card(
                    modifier = Modifier
                        .fillMaxHeight(0.80f)
                        .fillMaxWidth(0.40f),
                    shape = Shapes.rounded24Dp,
                    content = {
                        Image(
                            painter = painterResource(R.drawable.rih___rocky),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                )

            }
        }

        item {
            GroupedItemsWithTitleAndRoundedBorder(
                title = buildAnnotatedString {
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Templates")
                    }
                },
                shape = Shapes.rounded24Dp,
                trailingIcon = {
                    Surface(
                        shape = Shapes.rounded12Dp,
                        onClick = {},
                        color = Color.LightGray
                    ) {
                        Row(
                            modifier = Modifier.padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                            Text(
                                text = "add",
                                style = MaterialTheme.typography.labelSmall
                            )

                        }
                    }
                },
                content = {
                    repeat(3){
                        TemplatePreview()
                    }
                }
            )
        }

        item {
            GroupedItemsWithTitleAndRoundedBorder(
                title = buildAnnotatedString {
                    withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Drafts")
                    }
                },
                shape = Shapes.rounded24Dp,
                content = {
                    repeat(3){
                        TemplatePreview()
                    }
                }
            )
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun CreatePrev() {
    MenusMvpTheme {
        Create()
    }
}