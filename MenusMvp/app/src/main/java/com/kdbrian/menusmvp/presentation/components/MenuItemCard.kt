package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.di.Constants
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes
import src.main.graphql.fragment.BasicMenuItemInfo

@Composable
fun MenuItemCard(
    modifier: Modifier = Modifier,
    menuItemInfo: BasicMenuItemInfo? = null,
    onSelect: (BasicMenuItemInfo?) -> Unit = {},
) {

    Card(
        modifier = modifier
            .width(250.dp)
            .padding(6.dp),
        onClick = { onSelect(menuItemInfo!!) },
        shape = Shapes.rounded12Dp
    ) {
        val imageUrl = "${Constants.baseUrl}${menuItemInfo?.imageUrl}"

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = imageUrl,
                error = painterResource(R.drawable.rih___rocky),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .alpha(0.6f),
                shape = Shapes.rounded12Dp,
                color = Color.DarkGray,
                contentColor = Color.White,
                content = {}
            )



            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                RowWithIconAndText(
                    icon = R.drawable.menu,
                    spacing = 4.dp,
                    iconTint = Color.White,
                    textColor = Color.White,
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Yellow,
                                fontSize = 20.sp
                            )
                        ) {
                            append("${menuItemInfo?.name ?: ""}")
                        }
                    }
                )


                RowWithIconAndText(
                    icon = R.drawable.menu,
                    spacing = 4.dp,
                    iconTint = Color.White,
                    textColor = Color.White,
                    text = buildAnnotatedString {
                        withStyle(SpanStyle()) {
                            append("$ ${menuItemInfo?.price ?: ""}")
                        }
                    }
                )
            }

            Surface(
                modifier = Modifier.align(Alignment.BottomEnd),
                shape = RoundedCornerShape(topStart = 32.dp),
                color = Color.Blue,
                contentColor = Color.White
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                }
            }
        }

    }

}

@Preview
@Composable
private fun MenuItemCardPrev() {
    MenusMvpTheme {
        MenuItemCard()
    }
}