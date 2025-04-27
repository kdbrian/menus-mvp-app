package com.kdbrian.menusmvp.presentation.features.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.components.StackedCardsDisplay
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Shapes


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    onAction: (HomeScreenAction) -> Unit = {},
) {

    val (searchString, setSearchString) = remember { mutableStateOf("") }
    val forYouPagerState = rememberPagerState { 300 }
    val libraryPagerState = rememberPagerState { 300 }
    val similarPagerState = rememberPagerState { 300 }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("Good Day!\n")
                            }

                            withStyle(SpanStyle(fontSize = 16.sp)) {
                                append("Are you done exploring, start eating.")
                            }
                        }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                actions = {
                    IconButton(onClick = { onAction(HomeScreenAction.OpenSearch) }) {
                        Icon(imageVector = Icons.Rounded.Info, contentDescription = null)
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {

            item {
                GroupedItemsWithTitleAndRoundedBorder(
                    title =
                        buildAnnotatedString {
                            withStyle(SpanStyle()) {
                                append("For You")
                            }
                        },
                    trailingIcon = {
                        Surface(onClick = {}, shape = Shapes.rounded24Dp, color = Color.LightGray) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Refresh,
                                    contentDescription = null
                                )
                                Text(text = "Refresh", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    },
                    content = {
                        Column(modifier = Modifier) {
                            HorizontalPager(
                                state = forYouPagerState,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Surface(
                                    modifier = Modifier
                                        .height(250.dp)
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = Shapes.rounded12Dp,
                                    onClick = { onAction(HomeScreenAction.RestaurantInfo(it)) }
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable._df01cf9_2d01_4dc5_8b0c_3cda01c69d90),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }

                            AnimatedPagerIndicator(pagerState = forYouPagerState)
                        }
                    }
                )
            }


            item {
                var showLibraryItemInfo by remember { mutableStateOf(false) }

                GroupedItemsWithTitleAndRoundedBorder(
                    title =
                        buildAnnotatedString { withStyle(SpanStyle()){
                            append("Library")
                        }
                        },
                    trailingIcon = {
                        Surface(onClick = {}, shape = Shapes.rounded24Dp, color = Color.LightGray) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Refresh,
                                    contentDescription = null
                                )
                                Text(text = "Refresh", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    },
                    content = {
                        Column(modifier = Modifier) {
                            HorizontalPager(
                                state = libraryPagerState,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Surface(
                                    modifier = Modifier
                                        .height(250.dp)
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = Shapes.rounded12Dp,
                                    onClick = {}
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable._df01cf9_2d01_4dc5_8b0c_3cda01c69d90),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }

                            AnimatedPagerIndicator(pagerState = libraryPagerState)
                        }
                    }
                )
            }

            item {
                GroupedItemsWithTitleAndRoundedBorder(
                    title =  buildAnnotatedString { withStyle(SpanStyle()){
                        append("Similar")
                    }
                    },
                    trailingIcon = {
                        Surface(onClick = {}, shape = Shapes.rounded24Dp, color = Color.LightGray) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text(text = "View All", style = MaterialTheme.typography.labelSmall)
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    content = {
                        StackedCardsDisplay()
                    }
                )
            }

        }
    }


}


@Composable
fun AnimatedPagerIndicator(pagerState: PagerState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { index ->
            val isSelected = pagerState.currentPage == index

            // Animate size
            val size by animateDpAsState(
                targetValue = if (isSelected) 12.dp else 4.dp,
                animationSpec = tween(durationMillis = 300)
            )

            // Animate color
            val color by animateColorAsState(
                targetValue = if (isSelected) Color.DarkGray else Color.LightGray,
                animationSpec = tween(durationMillis = 300)
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    MenusMvpTheme {
        HomeScreen()
    }
}



