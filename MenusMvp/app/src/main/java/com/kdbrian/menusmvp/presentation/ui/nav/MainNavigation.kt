package com.kdbrian.menusmvp.presentation.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kdbrian.menusmvp.presentation.features.home.Catalogue
import com.kdbrian.menusmvp.presentation.features.system.HealthCheck

/*
* Controls  first class screens
* */
@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = First_Class_Route.HealthCheck) {

        composable<First_Class_Route.HealthCheck> {
            HealthCheck(
                onProceed = { navController.navigate(First_Class_Route.UsersHome) }
            )
        }

        composable<First_Class_Route.UsersHome> {
            Catalogue()
        }

        composable<First_Class_Route.Auth> { Box { } }

        composable<First_Class_Route.Premium> { Box { } }
    }

}
