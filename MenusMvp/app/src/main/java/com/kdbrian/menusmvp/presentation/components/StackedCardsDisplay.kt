package com.kdbrian.menusmvp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.util.Shapes


@Composable
fun StackedCardsDisplay() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(Shapes.rounded12Dp),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.50f)
                .fillMaxHeight(0.50f)
                .offset(x = -(24).dp, y = 12.dp)
                .padding(6.dp)
                .align(Alignment.CenterEnd),
            shape = Shapes.rounded24Dp,
            colors = CardDefaults.cardColors(containerColor = Color.Cyan)
        ) {

        }


        Card(
            modifier = Modifier
                .fillMaxWidth(0.50f)
                .fillMaxHeight(0.85f)
                .offset(x = 32.dp, y = 12.dp)
                .padding(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            shape = Shapes.rounded24Dp,
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {

        }


        Card(
            modifier = Modifier
                .fillMaxWidth(0.50f)
                .fillMaxHeight(0.95f)
                .offset(x = 12.dp, y = 6.dp)
                .padding(6.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = Shapes.rounded24Dp
        ) {

            Image(
                painter = painterResource(R.drawable.rih___rocky),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(0.50f)
                .fillMaxHeight()
                .padding(6.dp)
                .align(Alignment.CenterStart),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            shape = Shapes.rounded24Dp
        ) {

            Image(
                painter = painterResource(R.drawable._df01cf9_2d01_4dc5_8b0c_3cda01c69d90),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

    }
}