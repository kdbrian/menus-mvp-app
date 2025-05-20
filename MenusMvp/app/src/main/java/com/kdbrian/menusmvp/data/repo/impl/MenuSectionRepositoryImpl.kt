package com.kdbrian.menusmvp.data.repo.impl

import com.apollographql.apollo.ApolloClient
import com.kdbrian.menusmvp.domain.menus.MenuSectionRepository
import src.main.graphql.GetAllMenuSectionsQuery
import src.main.graphql.GetAllMenusQuery
import src.main.graphql.GetSectionsByIdQuery
import src.main.graphql.GetSectionsFromMenuQuery
import timber.log.Timber

class MenuSectionRepositoryImpl(
    private val apolloClient: ApolloClient
): MenuSectionRepository {

    override suspend fun getAllSections(): Result<GetAllMenuSectionsQuery.Data> {
        return apolloClient.query(GetAllMenuSectionsQuery()).execute().let { apolloResponse ->
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

    override suspend fun getSectionsByMenuId(menuId: String): Result<GetSectionsFromMenuQuery.Data> {
        return apolloClient.query(GetSectionsFromMenuQuery(menuId)).execute().let { apolloResponse ->
            if (apolloResponse.errors?.isNotEmpty() == true) {
                Result.failure(Exception(apolloResponse.exception?.message.toString()))
            } else {
                apolloResponse.data?.let {
//                    Timber.d("res $it")
                    Result.success(it)
                } ?: run {
//                    Timber.d("res ${apolloResponse.exception}")
                    Result.failure(Exception(apolloResponse.exception?.message.toString()))
                }
            }
        }

    }

    override suspend fun getSectionsById(sectionId: String): Result<GetSectionsByIdQuery.Data> {
        return apolloClient.query(GetSectionsByIdQuery(sectionId)).execute().let { apolloResponse ->
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