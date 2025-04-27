package com.kdbrian.menusmvp.data.repo.impl

import com.apollographql.apollo.ApolloClient
import com.kdbrian.menusmvp.data.remote.GraphqlServiceProvider
import com.kdbrian.menusmvp.domain.menus.MenuRepository
import src.main.graphql.GetAllMenusQuery
import src.main.graphql.GetMenuByIdQuery

class MenuRepositoryImpl : MenuRepository {

    private val apolloClient: ApolloClient = GraphqlServiceProvider.menusService

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
}