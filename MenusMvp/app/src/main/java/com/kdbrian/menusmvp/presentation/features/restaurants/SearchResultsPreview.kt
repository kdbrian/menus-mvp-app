package com.kdbrian.menusmvp.presentation.features.restaurants

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kdbrian.menusmvp.presentation.util.Resource
import src.main.graphql.FetchRestaurantByLevelQuery
import src.main.graphql.FetchRestaurantByPostalCodeQuery
import src.main.graphql.FetchRestaurantByZipCodeQuery

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsPreview(
    modifier: Modifier = Modifier,
    query: String,
    onClose: () -> Unit = {},
    restaurantsByZipCode: Resource<FetchRestaurantByZipCodeQuery.Data> = Resource.Nothing(),
    restaurantsByPostalCode: Resource<FetchRestaurantByPostalCodeQuery.Data> = Resource.Nothing(),
    restaurantsBylevel: Resource<FetchRestaurantByLevelQuery.Data> = Resource.Nothing(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = query, style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {

        }
    }

}