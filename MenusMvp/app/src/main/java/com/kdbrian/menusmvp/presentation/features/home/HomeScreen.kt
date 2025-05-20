package com.kdbrian.menusmvp.presentation.features.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.di.Constants
import com.kdbrian.menusmvp.presentation.components.CardWithTitleTagLineAndPreview
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Resource
import com.kdbrian.menusmvp.presentation.viewmodel.HomeScreenModel
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    homeScreenModel: HomeScreenModel = koinViewModel<HomeScreenModel>(),
    onAction: (HomeScreenAction) -> Unit = {},
) {
    val query by homeScreenModel.searchQuery.collectAsState()

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
                    var iconSize = animateDpAsState(targetValue = 150.dp)
                    IconButton(onClick = { onAction(HomeScreenAction.OpenScanQR) }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_qr_code_24),
                            contentDescription = null,
                            modifier = Modifier.size(iconSize.value)
                        )
                    }

                    IconButton(onClick = { onAction(HomeScreenAction.OpenSearch) }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null,
                            modifier = Modifier.size(iconSize.value)
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {

            item {
                val menusResource by homeScreenModel.menusResource.collectAsState()

                GroupedItemsWithTitleAndRoundedBorder(
                    title = buildAnnotatedString { append("Menus") },
                ) {
                    when (menusResource) {
                        is Resource.Error<*> -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = menusResource.message.toString())
                            }
                        }

                        is Resource.Loading<*> -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Loading")
                            }
                        }

                        is Resource.Nothing<*> -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = stringResource(R.string.nothing_here))
                            }
                        }

                        is Resource.Success<*> -> {
                            menusResource.data?.menus?.let { menus ->
                                val pagerState = rememberPagerState { menus.size }
                                HorizontalPager(
                                    state = pagerState,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    val info = menus[it]?.menuInfo
                                    CardWithTitleTagLineAndPreview(
                                        onSelect = {
                                            onAction(
                                                HomeScreenAction.OpenMenu(
                                                    info?.id ?: ""
                                                )
                                            )
                                        },
                                        previewUrl = "${Constants.baseUrl}${info?.bannerImage}",
                                        title = info?.name ?: "",
                                        tagline = info?.tagLine ?: ""
                                    )
                                }
                            }
                        }
                    }
                }
            }


            item {
                val restaurantResource by homeScreenModel.restaurantsResource.collectAsState()

                GroupedItemsWithTitleAndRoundedBorder(
                    title = buildAnnotatedString { append("Restaurants") },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    when (restaurantResource) {
                        is Resource.Error<*> -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = restaurantResource.message.toString())
                            }
                        }

                        is Resource.Loading<*> -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Loading")
                            }
                        }

                        is Resource.Nothing<*> -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = stringResource(R.string.nothing_here))
                            }
                        }

                        is Resource.Success<*> -> {
                            restaurantResource.data?.restaurants?.let { restaurants ->
                                val pagerState = rememberPagerState { restaurants.size }
                                HorizontalPager(
                                    state = pagerState,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    val info = restaurants[it]?.restaurantInfo
                                    CardWithTitleTagLineAndPreview(
                                        previewUrl = "${Constants.baseUrl}/${info?.bannerImage}",
                                        title = info?.name ?: "",
                                        tagline = if (info?.thumbsUp == 0.0) "" else "${info?.thumbsUp} 👍",
                                        onSelect = {}
                                    )
                                }
                            }
                        }
                    }
                }
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



