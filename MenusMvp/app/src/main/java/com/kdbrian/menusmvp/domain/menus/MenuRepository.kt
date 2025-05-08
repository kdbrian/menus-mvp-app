package com.kdbrian.menusmvp.domain.menus

import src.main.graphql.GetAllMenusQuery
import src.main.graphql.GetMenuByIdQuery
import src.main.graphql.ThumbsDownMenuMutation
import src.main.graphql.ThumbsUpMenuMutation

interface MenuRepository {
    suspend fun getAllMenus(): Result<GetAllMenusQuery.Data>
    suspend fun getMenuById(id: String): Result<GetMenuByIdQuery.Data>

    suspend fun thumbsUp(id: String): Result<ThumbsUpMenuMutation.Data>
    suspend fun thumbsDOwn(id: String): Result<ThumbsDownMenuMutation.Data>
}