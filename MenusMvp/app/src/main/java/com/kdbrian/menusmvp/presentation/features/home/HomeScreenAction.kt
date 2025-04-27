package com.kdbrian.menusmvp.presentation.features.home

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeScreenAction {
    data class RestaurantInfo(val img : Int) : HomeScreenAction()
    data object OpenSearch : HomeScreenAction()
    data object OpenMenus : HomeScreenAction()
    data object OpenRestaurants : HomeScreenAction()
}