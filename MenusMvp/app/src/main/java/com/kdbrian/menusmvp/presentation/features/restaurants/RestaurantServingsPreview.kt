package com.kdbrian.menusmvp.presentation.features.restaurants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.presentation.components.FilterMenuItemsBottomSheet
import com.kdbrian.menusmvp.presentation.components.MenuItemCard
import com.kdbrian.menusmvp.presentation.ui.theme.MenusMvpTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantServingsPreview(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
) {

    var isFiltersShown by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = LoremIpsum(3).values.joinToString(),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.padding(6.dp)
            )
        },
        snackbarHost = { }
    ) { padding ->

        LazyColumn(
            contentPadding = padding
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Sorting & Grouping",
                        style = MaterialTheme.typography.titleMedium
                    )


                    IconButton(onClick = { isFiltersShown = !isFiltersShown }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.List, // TODO: change to a filter icon
                            contentDescription = null,
                        )
                    }

                }


            }


            items(10) {
                MenuItemCard()
            }


        }

        if (isFiltersShown) {
            ModalBottomSheet(
                onDismissRequest = {
                    isFiltersShown = false
                }
            ) { FilterMenuItemsBottomSheet() }
        }
    }


}

@Preview
@Composable
private fun RestaurantServingsPreviewPrev() {
    MenusMvpTheme {
        RestaurantServingsPreview()
    }
}