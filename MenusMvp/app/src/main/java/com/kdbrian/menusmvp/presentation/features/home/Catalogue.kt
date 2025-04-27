package com.kdbrian.menusmvp.presentation.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kdbrian.menusmvp.presentation.components.BottomBarItem
import com.kdbrian.menusmvp.presentation.ui.nav.UsersHomeNavigation

@Composable
fun Catalogue(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val current = navController.currentBackStackEntryAsState().value?.destination

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                BottomBarItem.items.forEach { bottomBarItem ->

                    NavigationBarItem(
                        selected = bottomBarItem.secondClassRoute.toString() == current?.route.toString(),
                        onClick = {
                            navController.navigate(bottomBarItem.secondClassRoute) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = bottomBarItem.icon,
                                contentDescription = null
                            )
                        },
                    )
                }
            }
        }
    ) { paddingValues ->
        /*displays all other screens*/
        UsersHomeNavigation(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun Catalogue(
) {

}