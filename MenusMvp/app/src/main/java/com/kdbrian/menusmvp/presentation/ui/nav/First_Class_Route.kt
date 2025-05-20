package com.kdbrian.menusmvp.presentation.ui.nav

import kotlinx.serialization.Serializable

@Serializable
sealed class First_Class_Route {

    @Serializable
    data object HealthCheck : First_Class_Route()

    @Serializable
    data object UsersHome : First_Class_Route()

    @Serializable
    data object Premium : First_Class_Route()

    @Serializable
    data object Auth : First_Class_Route()

    @Serializable
    data object Scanner : First_Class_Route()

    @Serializable
    data class MenuInfo(val menuId: String): First_Class_Route()

    @Serializable
    data class MenuSection(val sectionId: String): First_Class_Route()


    @Serializable
    data class MenuItem(val itemId: String): First_Class_Route()


}