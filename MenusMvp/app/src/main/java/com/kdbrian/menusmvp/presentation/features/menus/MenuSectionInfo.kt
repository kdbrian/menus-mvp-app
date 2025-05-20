package com.kdbrian.menusmvp.presentation.features.menus

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SharedTransitionLayout
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.di.Constants
import com.kdbrian.menusmvp.presentation.components.FadingBackground
import com.kdbrian.menusmvp.presentation.components.GroupedItemsWithTitleAndRoundedBorder
import com.kdbrian.menusmvp.presentation.components.MenuItemCard
import com.kdbrian.menusmvp.presentation.components.MenuItemInfo
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme
import com.kdbrian.menusmvp.presentation.util.Resource
import com.kdbrian.menusmvp.presentation.util.Shapes
import kotlinx.coroutines.launch
import src.main.graphql.GetSectionsByIdQuery
import src.main.graphql.fragment.BasicMenuItemInfo
import src.main.graphql.fragment.DetailedMenuSectionInfo
import timber.log.Timber
import java.util.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuSectionPreview(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    onFavourite: (String) -> Unit = {},
    onMenuItemExpand: (String) -> Unit = {},/**/
    sectionResource: Resource<GetSectionsByIdQuery.Data>?,
) {

    var sectionBannerImage by remember { mutableStateOf<String?>(null) }
    var sectionById by remember {
        mutableStateOf<DetailedMenuSectionInfo?>(null)
    }

    var isMenuItemExpanded by remember { mutableStateOf(false) }
    var selectedMenuItem by remember { mutableStateOf<BasicMenuItemInfo?>(null) }
    val scope = rememberCoroutineScope()

    var isOptionsShown by remember { mutableStateOf(false) }

    LaunchedEffect(selectedMenuItem) {
        if (selectedMenuItem != null) {
            isMenuItemExpanded = true
        }
    }

    LaunchedEffect(sectionResource) {
        if (sectionResource is Resource.Success) {
            sectionBannerImage =
                "${Constants.baseUrl}/${sectionResource.data?.sectionsById?.detailedMenuSectionInfo?.bannerImage}"
            sectionById = sectionResource.data?.sectionsById?.detailedMenuSectionInfo
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = Shapes.rounded32Dp.copy(
                    topStart = CornerSize(0.dp),
                    topEnd = CornerSize(0.dp)
                ),
                shadowElevation = 6.dp
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {


                    AsyncImage(
                        model = sectionBannerImage,
                        error = painterResource(R.drawable.rih___rocky),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )


                    FadingBackground(opacity = 0.80f)


                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle()) { append(sectionById?.title ?: "") }
                            },
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Surface(
                        shape = Shapes.rounded24Dp,
                        onClick = onClose,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                    ) {

                        Icon(imageVector = Icons.Rounded.Close, contentDescription = null)

                    }

                    Surface(
                        shape = Shapes.rounded24Dp,
                        onClick = { isOptionsShown = !isOptionsShown }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(
                                text = "${kotlin.random.Random.nextInt(10, 500)}"
                            )
                            IconButton(
                                onClick = {
                                    if (sectionResource is Resource.Success)
                                        onFavourite(sectionResource.data?.sectionsById?.detailedMenuSectionInfo?.id!!)
                                },
                                modifier = Modifier
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Favorite,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {


            FadingBackground(opacity = 0.90f)


            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                when (sectionResource) {
                    is Resource.Error<*> -> {
                        Text(
                            text = sectionResource.message.toString()
                        )
                    }

                    is Resource.Loading<*> -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Resource.Nothing<*> -> {
                        Text(
                            text = stringResource(
                                R.string.nothing_here
                            )
                        )
                    }

                    is Resource.Success<*> -> {
                        val info = sectionResource.data?.sectionsById?.detailedMenuSectionInfo
                        GroupedItemsWithTitleAndRoundedBorder(
                            title = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontSize = MaterialTheme.typography.displaySmall.fontSize
                                    )
                                ) {
                                    append("Servings")
                                }
                            },
                        ) {

                            LazyVerticalGrid(
                                modifier = Modifier.fillMaxWidth(),
                                columns = Fixed(2)
                            ) {
                                val menuItemInfos = info?.menuItems?.map {
                                    it?.basicMenuItemInfo
                                }

                                menuItemInfos?.let { infos ->
                                    items(infos) {
                                        MenuItemCard(
                                            menuItemInfo = it,
                                            onSelect = {
                                                selectedMenuItem = it
//                                                isMenuItemExpanded = true
                                            }
                                        )
                                    }

                                }
                            }

                            Spacer(Modifier.height(30.dp))
                        }


                    }

                    null -> Unit
                }
            }

            AnimatedVisibility(
                visible = isOptionsShown,
                label = "options anime",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Surface(
                    shape = Shapes.rounded24Dp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(30.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        IconButton({}) {
                            Icon(
                                imageVector = Icons.Outlined.Share,
                                contentDescription = null
                            )
                        }

                        IconButton({}) {
                            Icon(
                                imageVector = Icons.Rounded.FavoriteBorder,
                                contentDescription = null
                            )
                        }

                        IconButton({}) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_bookmark_border_24),
                                contentDescription = null
                            )
                        }

                    }
                }
            }

        }

        if (isMenuItemExpanded) {
            val sheetState = rememberModalBottomSheetState()

            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                        isMenuItemExpanded = false
                        selectedMenuItem = null
                    }
                }
            ) {
                MenuItemInfo(
                    menuItemInfo = selectedMenuItem!!,
                    onExpand = { Timber.d("for $it") }
                )
            }
        }
    }

}


@Preview
@Composable
private fun MenuSectionPreviewPrev() {
    MenusMvpTheme {
        MenuSectionPreview(
            sectionResource = null
        )
    }
}