package com.kdbrian.menusmvp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.kdbrian.menusmvp.presentation.ui.nav.Second_Class_Route


sealed class BottomBarItem(
    val secondClassRoute: Second_Class_Route,
    val title: String,
    val icon: ImageVector,
){

    data object Home : BottomBarItem(
        secondClassRoute = Second_Class_Route.HomeScreen,
        title = "Home",
        icon = Icons.Rounded.Home
    )

    data object Create : BottomBarItem(
        secondClassRoute = Second_Class_Route.Create,
        title = "Create",
        icon = Icons.Rounded.AddCircle
    )

    data object Profile : BottomBarItem(
        secondClassRoute = Second_Class_Route.Profile,
        title = "Profile",
        icon = Icons.Rounded.AccountCircle
    )

    companion object{

        val items = listOf(
            Home, Create,Profile
        )
    }
}




@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {


}