package com.kdbrian.menusmvp.presentation.ui.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class Second_Class_Route() {

    @Serializable
    data object HomeScreen : Second_Class_Route()

    @Serializable
    data object Create : Second_Class_Route()

    @Serializable
    data object Profile : Second_Class_Route()

    @Serializable
    data object Search : Second_Class_Route()


    @Serializable
    data class RestaurantView(val id : String) : Second_Class_Route()


}