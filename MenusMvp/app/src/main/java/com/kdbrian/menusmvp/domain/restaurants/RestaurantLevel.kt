package com.kdbrian.menusmvp.domain.restaurants

sealed class RestaurantLevel(val `val`: Int) {
    data object One_Star : RestaurantLevel(1)
    data object Two_Star : RestaurantLevel(2)
    data object Three_Star : RestaurantLevel(3)
    data object Four_Star : RestaurantLevel(4)
    data object Five_Star : RestaurantLevel(5)
}

fun String.level(): RestaurantLevel {
    return when (this.substringAfter("l:")) {
        "1" -> RestaurantLevel.One_Star
        "2" -> RestaurantLevel.Two_Star
        "3" -> RestaurantLevel.Three_Star
        "4" -> RestaurantLevel.Four_Star
        "5" -> RestaurantLevel.Five_Star
        else -> throw IllegalArgumentException("Invalid restaurant level.")
    }
}