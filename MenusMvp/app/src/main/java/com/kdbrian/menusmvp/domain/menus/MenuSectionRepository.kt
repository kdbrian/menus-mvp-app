package com.kdbrian.menusmvp.domain.menus

import src.main.graphql.GetAllMenuSectionsQuery
import src.main.graphql.GetSectionsByIdQuery
import src.main.graphql.GetSectionsFromMenuQuery

interface MenuSectionRepository {
    suspend fun getAllSections(): Result<GetAllMenuSectionsQuery.Data>
    suspend fun getSectionsByMenuId(menuId: String): Result<GetSectionsFromMenuQuery.Data>
    suspend fun getSectionsById(sectionId: String): Result<GetSectionsByIdQuery.Data>
}