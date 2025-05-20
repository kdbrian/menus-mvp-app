package com.kdbrian.menusmvp.presentation.features.restaurants.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import src.main.graphql.fragment.RestaurantInfo

@Composable
fun RestaurantInfoPreview(
    modifier: Modifier = Modifier,
    restaurantInfo: RestaurantInfo,
    onIdClick: (String) -> Unit = {},
) {

    val clipBoardManager = LocalClipboardManager.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp), shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                text = "#${restaurantInfo.id}",
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray,
                modifier = Modifier.clickable {
                    clipBoardManager.setText(AnnotatedString(restaurantInfo.id))
                    onIdClick(restaurantInfo.id)
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = restaurantInfo.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )

            }


            /*Text(
                text = buildAnnotatedString {

                    withStyle(SpanStyle()) {
                        append(restaurantInfo.zipCode.toString())
                    }

                    withStyle(SpanStyle()) {
                        append(", ")
                    }
                    withStyle(SpanStyle()) {
                        append(restaurantInfo.postalCode.toString())
                    }
                },
                style = MaterialTheme.typography.titleSmall,
            )*/

        }

    }
}