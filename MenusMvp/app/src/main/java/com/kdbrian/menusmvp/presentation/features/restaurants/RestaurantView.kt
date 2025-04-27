package com.kdbrian.menusmvp.presentation.features.restaurants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantView(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    onSaveRestaurant: () -> Unit = {},
) {

    val verticalScrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSaveRestaurant) {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(verticalScrollState)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(250.dp)
                    .clip(Shapes.rounded12Dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.rih___rocky),
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )

                Surface(
                    content = {},
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.4f)
                )

                Surface(
                    color = Color.Transparent,
                    contentColor = Color.White,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(6.dp)
                ) {
                    Text(
                        text = LoremIpsum(3).values.joinToString(),
                        modifier = Modifier.align(Alignment.BottomStart),
                        style = MaterialTheme.typography.titleLarge
                    )
                }

            }

            GroupedItemsWithTitleAndRoundedBorder(
                title = buildAnnotatedString {
                    withStyle(SpanStyle()) {
                        append("Servings")
                    }
                },
                content = {
                repeat(5) {
                    Card(modifier = Modifier.fillMaxWidth(), shape = Shapes.rounded12Dp) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = LoremIpsum(Random.nextInt(2, 4)).values.joinToString(),
                                modifier = Modifier,
                                style = MaterialTheme.typography.labelLarge
                            )

                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            )


        }
    }

}


@Preview
@Composable
private fun RestaurantViewPrev() {
    MenusMvpTheme {
        RestaurantView()
    }
}