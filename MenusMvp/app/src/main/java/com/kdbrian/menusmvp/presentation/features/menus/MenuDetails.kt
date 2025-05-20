package com.kdbrian.menusmvp.presentation.features.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.di.Constants
import com.kdbrian.menusmvp.presentation.components.CardWithTitleTagLineAndPreview
import com.kdbrian.menusmvp.presentation.components.FadingBackground
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.components.RestaurantPreview
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Resource
import com.kdbrian.menusmvp.presentation.util.Shapes
import src.main.graphql.GetMenuByIdQuery
import src.main.graphql.fragment.MenuInfoDetailed
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDetails(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    onShare: (GetMenuByIdQuery.Data) -> Unit = {},
    onSectionExpand: (String) -> Unit = {},
    menuResource: Resource<GetMenuByIdQuery.Data>,
) {

    val padded8Dp = Modifier.padding(8.dp)
    var imageUrl by remember {
        mutableStateOf<String?>(null)
    }
    var menuDetails by remember { mutableStateOf<MenuInfoDetailed?>(null) }
    LaunchedEffect(menuResource) {
        if (menuResource is Resource.Success) {
            menuDetails = menuResource.data?.menuById?.menuInfoDetailed
            imageUrl = "${Constants.baseUrl}${menuDetails?.bannerImage}"
        }
    }


    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.LightGray,
                        Color.Black
                    )
                )
            ),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = buildAnnotatedString {
                            append(menuDetails?.name ?: "Menu Info")
                        }
                    )
                },
                navigationIcon = {
                    Surface(shape = CircleShape) {
                        IconButton(onClose) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    Surface(shape = CircleShape) {
                        IconButton({ onShare(menuResource.data!!) }) {
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                contentDescription = null
                            )
                        }
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            item {
                // Large Image Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imageUrl,
                        error = painterResource(R.drawable.rih___rocky),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    FadingBackground()

                    Row(
                        modifier = padded8Dp
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Card(
                            modifier = Modifier.size(150.dp),
                            shape = Shapes.rounded12Dp,
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            AsyncImage(
                                model = imageUrl,
                                error = painterResource(R.drawable.rih___rocky),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        Spacer(Modifier.width(8.dp))

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = buildAnnotatedString {
                                    append(menuDetails?.name)
                                },
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                            )

                            Text(
                                text = buildAnnotatedString {
                                    if (menuDetails?.menuSections?.isNotEmpty() == true)
                                        append("${menuDetails?.menuSections?.size} sections")
                                },
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = padded8Dp
                ) {

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append("Served By")
                            }
                        }
                    )

                    RestaurantPreview(
                        basicRestaurantInfo = menuResource.data?.menuById?.menuInfoDetailed?.restaurant
                    )

                }

            }

            item {

                GroupedItemsWithTitleAndRoundedBorder(
                    title = buildAnnotatedString { append("Sections") },
                    trailingIcon = {
                        Box {
                            IconButton({}) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                            }
                        }
                    }
                ) {
                    if (menuResource is Resource.Success) {
                        val sections = menuDetails?.menuSections
                        val pagerState = rememberPagerState { sections?.size ?: 0 }
                        HorizontalPager(
                            state = pagerState
                        ) {
                            val curr = sections?.get(it)?.basicMenuSectionInfo
                            val currPreview = "${Constants.baseUrl}${curr?.bannerImage}"

                            CardWithTitleTagLineAndPreview(
                                previewUrl = currPreview,
                                title = curr?.title ?: "",
                                tagline = "",
                                onSelect = {
                                    Timber.d("opening ${curr?.id!!}")
                                    onSectionExpand(curr.id)
                                }
                            )
                        }
                    }
                }

            }
        }


    }
}


@Preview
@Composable
private fun MenuInfoPrev() {
    MenusMvpTheme {
//        MenuInfo()
    }
}