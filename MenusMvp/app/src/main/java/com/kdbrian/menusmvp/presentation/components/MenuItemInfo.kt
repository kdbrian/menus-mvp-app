package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.R
import src.main.graphql.fragment.BasicMenuItemInfo
import com.kdbrian.menusmvp.di.Constants
import com.kdbrian.menusmvp.presentation.ui.theme.combo_regular
import com.kdbrian.menusmvp.presentation.ui.theme.rubikmarkerhatchregular

@Composable
fun MenuItemInfo(
    modifier: Modifier = Modifier,
    menuItemInfo: BasicMenuItemInfo,
    onExpand: (String) -> Unit = {},
) {

    val imageUrl = "${Constants.baseUrl}/${menuItemInfo.imageUrl}"

    menuItemInfo.name
    menuItemInfo.price
    menuItemInfo.id

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                error = painterResource(R.drawable.rih___rocky),
                contentDescription = menuItemInfo.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            FadingBackground()

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = buildAnnotatedString {
                        append(menuItemInfo.name)
                    },
                    modifier = Modifier
                        .padding(12.dp),
                    style = MaterialTheme.typography.displaySmall,
                    fontFamily = rubikmarkerhatchregular
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = combo_regular
                            )
                        ) { append("${menuItemInfo.price}") }
                    }
                )
            }


            Surface(
                shape = CircleShape,
                onClick = {
                    onExpand(menuItemInfo.id)
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(12.dp)
            ) {
                IconButton(onClick = {
                    onExpand(menuItemInfo.id)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_zoom_out_map_24),
                        contentDescription = "expand"
                    )
                }
            }
        }


    }
}