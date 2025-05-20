package com.kdbrian.menusmvp.data.repo.impl

import com.apollographql.apollo.ApolloClient
import com.kdbrian.menusmvp.domain.menus.MenuRepository
import src.main.graphql.GetAllMenusQuery
import src.main.graphql.GetMenuByIdQuery
import src.main.graphql.ThumbsDownMenuMutation
import src.main.graphql.ThumbsUpMenuMutation

class MenuRepositoryImpl(
    private val apolloClient: ApolloClient
) : MenuRepository {

    override suspend fun getAllMenus(): Result<GetAllMenusQuery.Data> {
        return apolloClient.query(GetAllMenusQuery()).execute().let { apolloResponse ->
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

    override suspend fun getMenuById(id: String): Result<GetMenuByIdQuery.Data> {
        return apolloClient.query(GetMenuByIdQuery(id)).execute().let { apolloResponse ->
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

    override suspend fun thumbsUp(id: String): Result<ThumbsUpMenuMutation.Data> {
        return apolloClient.mutation(ThumbsUpMenuMutation(id)).execute().let { apolloResponse ->
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

    override suspend fun thumbsDOwn(id: String): Result<ThumbsDownMenuMutation.Data> {
        return apolloClient.mutation(ThumbsDownMenuMutation(id)).execute().let { apolloResponse ->
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