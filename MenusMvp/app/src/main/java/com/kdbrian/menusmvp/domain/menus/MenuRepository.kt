package com.kdbrian.menusmvp.domain.menus

import src.main.graphql.GetAllMenusQuery
import src.main.graphql.GetMenuByIdQuery

interface MenuRepository {
    suspend fun getAllMenus() : Result<GetAllMenusQuery.Data>
    suspend fun getMenuById(id : String) : Result<GetMenuByIdQuery.Data>
}