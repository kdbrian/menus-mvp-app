package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.BuildConfig
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes
import src.main.graphql.fragment.MenuInfoDetailed


@Composable
        /*
        * Requires:
        *
        fragment RestaurantInfo on Restaurant {
            id
            name
            level
            postalCode
            zipCode
            bannerImage
        }
        * */
fun RestaurantPreview(
    modifier: Modifier = Modifier,
    basicRestaurantInfo: MenuInfoDetailed.Restaurant? = null,
    onSelect: (MenuInfoDetailed.Restaurant?) -> Unit = {},
) {

    val restaurantInfo = basicRestaurantInfo?.restaurantInfo

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        onClick = { onSelect(basicRestaurantInfo) },
        shape = Shapes.rounded24Dp,
//        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(12.dp)
        ) {

            Card(modifier = Modifier.size(70.dp), shape = Shapes.rounded12Dp) {
                val bannerImage = "${BuildConfig.serverUrl}${restaurantInfo?.bannerImage}"
                AsyncImage(
                    model = bannerImage,
                    error = painterResource(R.drawable.rih___rocky), // TODO : Attach a restaurant placeholder
                    contentDescription = restaurantInfo?.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(restaurantInfo?.name)
                    },
                    style = MaterialTheme.typography.titleLarge
                )


                Text(
                    text = buildAnnotatedString {
                        append(
                            "${restaurantInfo?.thumbsUp} 👍"
                        )
                    },
                    style = MaterialTheme.typography.labelSmall
                )
            }

        }

    }
}


@Preview
@Composable
private fun RestaurantPreviewPrev() {
    MenusMvpTheme {
        RestaurantPreview()
    }
}