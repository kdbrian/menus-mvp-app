package com.kdbrian.menusmvp.domain.restaurants

import src.main.graphql.FetchRestaurantByIdQuery
import src.main.graphql.FetchRestaurantByLevelQuery
import src.main.graphql.FetchRestaurantByPostalCodeQuery
import src.main.graphql.FetchRestaurantByZipCodeQuery
import src.main.graphql.FetchRestaurantsQuery
import src.main.graphql.ThumbDownRestaurantMutation
import src.main.graphql.ThumbUpRestaurantMutation

interface RestaurantRepository {
    suspend fun getAllRestaurants(): Result<FetchRestaurantsQuery.Data>
    suspend fun getRestaurantById(id: String): Result<FetchRestaurantByIdQuery.Data>
    suspend fun getRestaurantByLevel(level: RestaurantLevel): Result<FetchRestaurantByLevelQuery.Data>
    suspend fun getRestaurantByZipCode(zipCode: String): Result<FetchRestaurantByZipCodeQuery.Data>
    suspend fun getRestaurantByPostalCode(postalCode: String): Result<FetchRestaurantByPostalCodeQuery.Data>

    suspend fun thumbUpRestaurant(restaurantId : String): Result<ThumbUpRestaurantMutation.Data>
    suspend fun thumbDownRestaurant(restaurantId: String): Result<ThumbDownRestaurantMutation.Data>
}