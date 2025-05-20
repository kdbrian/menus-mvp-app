package com.kdbrian.menusmvp.presentation.features.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import kotlinx.serialization.Serializable

@Serializable
object OnBoardingScreen


@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 100.dp)
    ) {

        item {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.rih___rocky),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }

        item {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .rotate(60f)
                    .offset(x = 30.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.rih___rocky),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }


        item {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.rih___rocky),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }

    }
}

@Preview
@Composable
private fun OnBoardingScreenPrev() {
    MenusMvpTheme {
        OnBoardingScreen()
    }
}