package com.kdbrian.menusmvp.data.repo.impl

import com.apollographql.apollo.ApolloClient
import com.kdbrian.menusmvp.domain.restaurants.RestaurantRepository
import com.kdbrian.menusmvp.domain.restaurants.RestaurantLevel
import src.main.graphql.FetchRestaurantByIdQuery
import src.main.graphql.FetchRestaurantByLevelQuery
import src.main.graphql.FetchRestaurantByPostalCodeQuery
import src.main.graphql.FetchRestaurantByZipCodeQuery
import src.main.graphql.FetchRestaurantsQuery
import src.main.graphql.GetAllMenusQuery
import src.main.graphql.ThumbDownRestaurantMutation
import src.main.graphql.ThumbUpRestaurantMutation
import src.main.graphql.ThumbsDownMenuMutation
import src.main.graphql.ThumbsUpMenuMutation
import timber.log.Timber

class RestaurantRepositoryImpl(
    private val apolloClient: ApolloClient
) : RestaurantRepository {

    override suspend fun getAllRestaurants(): Result<FetchRestaurantsQuery.Data> {
        return apolloClient.query(FetchRestaurantsQuery()).execute().let { apolloResponse ->
            Timber.d("${apolloResponse.errors?.isNotEmpty()}, ${apolloResponse.operation}")
            if (apolloResponse.errors?.isNotEmpty() == true) {
                Timber.d(apolloResponse.errors.toString())
                Result.failure(Exception(apolloResponse.exception?.message.toString()))
            } else {
                apolloResponse.data?.let {
                    Result.success(it)
                } ?: run {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                }
            }
        }
    }

    override suspend fun getRestaurantById(id: String): Result<FetchRestaurantByIdQuery.Data> {
        return apolloClient.query(FetchRestaurantByIdQuery(id)).execute().let { apolloResponse ->
            if (apolloResponse.errors?.isNotEmpty() == true) {
                Result.failure(Exception(apolloResponse.exception?.message.toString()))
            } else {
                apolloResponse.data?.let {
                    Result.success(it)
                } ?: run {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                }
            }
        }
    }

    override suspend fun getRestaurantByLevel(level: RestaurantLevel): Result<FetchRestaurantByLevelQuery.Data> {
        return apolloClient.query(FetchRestaurantByLevelQuery(level.`val`)).execute()
            .let { apolloResponse ->
                if (apolloResponse.errors?.isNotEmpty() == true) {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                } else {
                    apolloResponse.data?.let {
                        Result.success(it)
                    } ?: run {
                        Result.failure(Exception(apolloResponse.exception?.message.toString()))
                    }
                }
            }

    }

    override suspend fun getRestaurantByZipCode(zipCode: String): Result<FetchRestaurantByZipCodeQuery.Data> {
        return apolloClient.query(FetchRestaurantByZipCodeQuery(zipCode)).execute()
            .let { apolloResponse ->
                if (apolloResponse.errors?.isNotEmpty() == true) {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                } else {
                    apolloResponse.data?.let {
                        Result.success(it)
                    } ?: run {
                        Result.failure(Exception(apolloResponse.exception?.message.toString()))
                    }
                }
            }

    }

    override suspend fun getRestaurantByPostalCode(postalCode: String): Result<FetchRestaurantByPostalCodeQuery.Data> {
        return apolloClient.query(FetchRestaurantByPostalCodeQuery(postalCode)).execute()
            .let { apolloResponse ->
                if (apolloResponse.errors?.isNotEmpty() == true) {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                } else {
                    apolloResponse.data?.let {
                        Result.success(it)
                    } ?: run {
                        Result.failure(Exception(apolloResponse.exception?.message.toString()))
                    }
                }
            }

    }

    override suspend fun thumbUpRestaurant(restaurantId: String): Result<ThumbUpRestaurantMutation.Data> {
        return apolloClient.mutation(ThumbUpRestaurantMutation(restaurantId)).execute()
            .let { apolloResponse ->
                if (apolloResponse.errors?.isNotEmpty() == true) {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                } else {
                    apolloResponse.data?.let {
                        Result.success(it)
                    } ?: run {
                        Result.failure(Exception(apolloResponse.exception?.message.toString()))
                    }
                }
            }
    }

    override suspend fun thumbDownRestaurant(restaurantId: String): Result<ThumbDownRestaurantMutation.Data> {
        return apolloClient.mutation(ThumbDownRestaurantMutation(restaurantId)).execute()
            .let { apolloResponse ->
                if (apolloResponse.errors?.isNotEmpty() == true) {
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                } else {
                    apolloResponse.data?.let {
                        Result.success(it)
                    } ?: run {
                        Result.failure(Exception(apolloResponse.exception?.message.toString()))
                    }
                }
            }
    }

}