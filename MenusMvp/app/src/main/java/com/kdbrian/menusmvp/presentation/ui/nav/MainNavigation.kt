package com.kdbrian.menusmvp.presentation.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kdbrian.menusmvp.presentation.features.home.Catalogue
import com.kdbrian.menusmvp.presentation.features.menus.MenuDetails
import com.kdbrian.menusmvp.presentation.features.menus.MenuSectionPreview
import com.kdbrian.menusmvp.presentation.features.system.HealthCheck
import com.kdbrian.menusmvp.presentation.viewmodel.MenuSectionViewModel
import com.kdbrian.menusmvp.presentation.viewmodel.MenusViewModel
import org.koin.compose.viewmodel.koinViewModel
import timber.log.Timber

/*
* Controls  first class screens
* */
@Composable
fun MainNavigation() {

    val navController = rememberNavController()
    val menusViewModel = koinViewModel<MenusViewModel>()
    val sectionViewModel = koinViewModel<MenuSectionViewModel>()

    val openMenu: (String) -> Unit = {
        navController.navigate(First_Class_Route.MenuInfo(it))
    }

    val openMenuSection: (String) -> Unit = {
        Timber.d("opening $it")
        navController.navigate(First_Class_Route.MenuSection(it))
    }

    val onScan: () -> Unit = {
        navController.navigate(First_Class_Route.Scanner)
    }

    val onMenuItemExpand: (String) -> Unit = {
        navController.navigate(First_Class_Route.MenuItem(it))
    }


    NavHost(navController = navController, startDestination = First_Class_Route.HealthCheck) {

        composable<First_Class_Route.HealthCheck> {
            HealthCheck(
                onProceed = { navController.navigate(First_Class_Route.UsersHome) }
            )
        }

        composable<First_Class_Route.UsersHome> {
            Catalogue(
                onOpenMenu = openMenu,
                onScan = onScan
            )
        }

        composable<First_Class_Route.MenuInfo> {

            val id = it.toRoute<First_Class_Route.MenuInfo>()
            val menuResource = menusViewModel.menuById.collectAsState()

            LaunchedEffect(Unit) {
                menusViewModel.getMenuById(id.menuId)
            }

            MenuDetails(
                onSectionExpand = openMenuSection,
                onClose = { navController.popBackStack() },
                menuResource = menuResource.value
            )
        }

        composable<First_Class_Route.MenuSection> {

            val id = it.toRoute<First_Class_Route.MenuSection>()

            LaunchedEffect(Unit) {
                sectionViewModel.getSectionById(id.sectionId)
            }

            val sectionByIdResource = sectionViewModel.sectionById.collectAsState()

            MenuSectionPreview(
                onClose = { navController.popBackStack() },
                sectionResource = sectionByIdResource.value
            )
        }

        composable<First_Class_Route.MenuItem> {
            val menuItem=  it.toRoute<First_Class_Route.MenuItem>()
            menusViewModel.
        }

        composable<First_Class_Route.Auth> { Box { } }

        composable<First_Class_Route.Scanner> { Box { } }

        composable<First_Class_Route.Premium> { Box { } }

    }

}
