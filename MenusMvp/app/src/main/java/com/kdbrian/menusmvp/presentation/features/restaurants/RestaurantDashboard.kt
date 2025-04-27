package com.kdbrian.menusmvp.presentation.features.restaurants

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.kdbrian.menusmvp.R
import com.kdbrian.menusmvp.presentation.features.restaurants.components.RestaurantInfoPreview
import com.kdbrian.menusmvp.presentation.util.Resource
import com.kdbrian.menusmvp.presentation.viewmodel.RestaurantViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import src.main.graphql.FetchRestaurantsQuery

@Serializable
data object RestaurantDashboard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDashboard(
    modifier: Modifier = Modifier,
    restaurantViewModel: RestaurantViewModel,
    onNavigateBack : () ->Unit={}
) {

    val restaurantResource by restaurantViewModel.restaurants.collectAsState()

    val (searchString, setSearchString) = remember { mutableStateOf("") }
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    var isAdvanceSearchInstructionsShown by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current


    LaunchedEffect(restaurantResource) {
        if (restaurantResource is Resource.Error) {
            scope.launch {
                snackbarHostState.showSnackbar(restaurantResource.message.toString())
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {

            when (restaurantResource) {
                is Resource.Error -> Unit
                is Resource.Loading -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        CircularProgressIndicator()
                        Text(text = stringResource(R.string.loading), textAlign = TextAlign.Center)
                    }
                }

                is Resource.Nothing -> {
                    Text(
                        text = stringResource(R.string.nothing_here),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                is Resource.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        restaurantResource.data?.restaurants?.let { restaurants ->

                            items(restaurants) { r ->
                                RestaurantInfoPreview(
                                    restaurantInfo = r!!.restaurantInfo,
                                    onIdClick = {
                                        setSearchString("id:$it")
                                        keyboardController?.hide()
                                        //invoke search

                                    }
                                )
                            }

                        }
                    }
                }
            }


        }

    }


}