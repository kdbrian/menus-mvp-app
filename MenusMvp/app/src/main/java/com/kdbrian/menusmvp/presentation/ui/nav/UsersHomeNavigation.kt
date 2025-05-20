package com.kdbrian.menusmvp.presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kdbrian.menusmvp.presentation.features.creators.Create
import com.kdbrian.menusmvp.presentation.features.home.HomeScreen
import com.kdbrian.menusmvp.presentation.features.home.HomeScreenAction
import com.kdbrian.menusmvp.presentation.features.profile.Profile
import com.kdbrian.menusmvp.presentation.features.restaurants.RestaurantView
import com.kdbrian.menusmvp.presentation.features.search.SearchScreen
import timber.log.Timber


@Composable
fun UsersHomeNavigation(
    modifier: Modifier = Modifier,
    onScan: () -> Unit,
    onOpenMenu: (String) -> Unit,
    navController: NavHostController,
) {

    val onOpenRestaurantInfo: (String) -> Unit = {
        navController.navigate(Second_Class_Route.RestaurantView(it))
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Second_Class_Route.HomeScreen
    ) {

        composable<Second_Class_Route.HomeScreen> {
            HomeScreen(
                onAction = { action ->
                    when (action) {
                        is HomeScreenAction.OpenMenus -> Unit
                        is HomeScreenAction.OpenRestaurants -> Unit
                        is HomeScreenAction.RestaurantInfo -> {
                            val id = action.img
                            Timber.d("id $id")
                            onOpenRestaurantInfo("$id")
                        }

                        is HomeScreenAction.OpenSearch -> {
                            navController.navigate(Second_Class_Route.Search)
                        }
                        is HomeScreenAction.OpenScanQR -> onScan
                        is HomeScreenAction.OpenMenu -> {
                            onOpenMenu(action.id)
                        }
                    }
                }
            )
        }

        composable<Second_Class_Route.Create> {
            Create()
        }

        composable<Second_Class_Route.Profile> {
            Profile()
        }

        composable<Second_Class_Route.RestaurantView> {
            RestaurantView()
        }

        composable<Second_Class_Route.Search> {
            SearchScreen(
                onClose = { navController.popBackStack() }
            )
        }

    }

}