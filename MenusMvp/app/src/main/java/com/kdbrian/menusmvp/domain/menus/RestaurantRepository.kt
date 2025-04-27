package com.kdbrian.menusmvp.domain.menus

import com.kdbrian.menusmvp.domain.restaurants.RestaurantLevel
import src.main.graphql.FetchRestaurantByIdQuery
import src.main.graphql.FetchRestaurantByLevelQuery
import src.main.graphql.FetchRestaurantByPostalCodeQuery
import src.main.graphql.FetchRestaurantByZipCodeQuery
import src.main.graphql.FetchRestaurantsQuery

interface RestaurantRepository {
    suspend fun getAllRestaurants(): Result<FetchRestaurantsQuery.Data>
    suspend fun getRestaurantById(id: String): Result<FetchRestaurantByIdQuery.Data>
    suspend fun getRestaurantByLevel(level: RestaurantLevel): Result<FetchRestaurantByLevelQuery.Data>
    suspend fun getRestaurantByZipCode(zipCode: String): Result<FetchRestaurantByZipCodeQuery.Data>
    suspend fun getRestaurantByPostalCode(postalCode: String): Result<FetchRestaurantByPostalCodeQuery.Data>
}