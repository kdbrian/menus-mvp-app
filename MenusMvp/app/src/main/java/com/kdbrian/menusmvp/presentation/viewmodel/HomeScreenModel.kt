package com.kdbrian.menusmvp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.menusmvp.domain.menus.MenuRepository
import com.kdbrian.menusmvp.domain.restaurants.RestaurantRepository
import com.kdbrian.menusmvp.presentation.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import src.main.graphql.FetchRestaurantsQuery
import src.main.graphql.GetAllMenusQuery
import timber.log.Timber

class HomeScreenModel(
    private val restaurantRepository: RestaurantRepository,
    private val menuRepository: MenuRepository,
) : ViewModel() {

    private val _restaurantsResource: MutableStateFlow<Resource<FetchRestaurantsQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val restaurantsResource: StateFlow<Resource<FetchRestaurantsQuery.Data>>
        get() = _restaurantsResource.asStateFlow()

    private val _menusResource: MutableStateFlow<Resource<GetAllMenusQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val menusResource: StateFlow<Resource<GetAllMenusQuery.Data>>
        get() = _menusResource.asStateFlow()

    private val _searchQuery: MutableStateFlow<String?> = MutableStateFlow(null)
    val searchQuery: StateFlow<String?>
        get() = _searchQuery.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    init {
        viewModelScope.launch {

            restaurantRepository
                .getAllRestaurants()
                .fold(
                    onSuccess = {
                        Timber.d("suzz $it")
                        _restaurantsResource.emit(Resource.Success(it))
                    },
                    onFailure = {
                        Timber.d("errzz $it")
                        _restaurantsResource.emit(Resource.Error(it.message.toString()))
                    },
                )

            menuRepository
                .getAllMenus()
                .fold(
                    onSuccess = { _menusResource.emit(Resource.Success(it)) },
                    onFailure = { _menusResource.emit(Resource.Error(it.message.toString())) },
                )

        }
    }

}