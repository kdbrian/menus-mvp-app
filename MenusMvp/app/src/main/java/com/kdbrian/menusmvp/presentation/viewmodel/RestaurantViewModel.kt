package com.kdbrian.menusmvp.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.menusmvp.domain.menus.RestaurantRepository
import com.kdbrian.menusmvp.domain.restaurants.level
import com.kdbrian.menusmvp.presentation.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import src.main.graphql.FetchRestaurantByIdQuery
import src.main.graphql.FetchRestaurantByLevelQuery
import src.main.graphql.FetchRestaurantByPostalCodeQuery
import src.main.graphql.FetchRestaurantByZipCodeQuery
import src.main.graphql.FetchRestaurantsQuery

class RestaurantViewModel(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {


    private val _restaurants: MutableStateFlow<Resource<FetchRestaurantsQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val restaurants: StateFlow<Resource<FetchRestaurantsQuery.Data>>
        get() = _restaurants.asStateFlow()


    private val _restaurantById: MutableStateFlow<Resource<FetchRestaurantByIdQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val restaurantById: StateFlow<Resource<FetchRestaurantByIdQuery.Data>>
        get() = _restaurantById.asStateFlow()


    private val _restaurantByLevel: MutableStateFlow<Resource<FetchRestaurantByLevelQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val restaurantByLevel: StateFlow<Resource<FetchRestaurantByLevelQuery.Data>>
        get() = _restaurantByLevel.asStateFlow()


    private val _restaurantByZipCode: MutableStateFlow<Resource<FetchRestaurantByZipCodeQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val restaurantByZipCode: StateFlow<Resource<FetchRestaurantByZipCodeQuery.Data>>
        get() = _restaurantByZipCode.asStateFlow()

    private val _restaurantByPostalCode: MutableStateFlow<Resource<FetchRestaurantByPostalCodeQuery.Data>> =
        MutableStateFlow(Resource.Loading())
    val restaurantByPostalCode: StateFlow<Resource<FetchRestaurantByPostalCodeQuery.Data>>
        get() = _restaurantByPostalCode.asStateFlow()

    init {
        viewModelScope.launch {
            _restaurants.emit(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                _restaurants.emit(
                    restaurantRepository.getAllRestaurants().fold(
                        onSuccess = {
                            Resource.Success(it)
                        },
                        onFailure = {
                            Resource.Error(it.message.toString())
                        }
                    )
                )
            }
        }
    }

    fun getRestaurantById(id: String) {
        viewModelScope.launch {
            _restaurantById.emit(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                _restaurantById.emit(
                    restaurantRepository.getRestaurantById(id).fold(
                        onSuccess = {
                            Resource.Success(it)
                        },
                        onFailure = {
                            Resource.Error(it.message.toString())
                        }
                    )
                )
            }

        }
    }

    fun getRestaurantByLevel(level: String) {
        viewModelScope.launch {
            _restaurantByLevel.emit(Resource.Loading())

            if (!level.startsWith("l:") || level.indexOf(",") != -1)
                _restaurantByLevel.emit(Resource.Error("Invalid search query"))
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    _restaurantByLevel.emit(
                        restaurantRepository.getRestaurantByLevel(level.level()).fold(
                            onSuccess = {
                                Resource.Success(it)
                            },
                            onFailure = {
                                Resource.Error(it.message.toString())
                            }
                        )
                    )
                }
            }

        }
    }

    fun getRestaurantByZipCode(zipCode: String) {
        viewModelScope.launch {
            _restaurantByZipCode.emit(Resource.Loading())
            if (!zipCode.startsWith("zc:") || zipCode.indexOf(",") != -1)
                _restaurantByZipCode.emit(Resource.Error("Invalid search query"))
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    _restaurantByZipCode.emit(
                        restaurantRepository.getRestaurantByZipCode(zipCode).fold(
                            onSuccess = {
                                Resource.Success(it)
                            },
                            onFailure = {
                                Resource.Error(it.message.toString())
                            }
                        )
                    )
                }
            }

        }
    }

    fun getRestaurantByPostalCode(postalCode: String) {
        viewModelScope.launch {
            _restaurantByPostalCode.emit(Resource.Loading())
            if (!postalCode.startsWith("zc:") || postalCode.indexOf(",") != -1)
                _restaurantByZipCode.emit(Resource.Error("Invalid search query"))
            else {
                CoroutineScope(Dispatchers.IO).launch {
                    _restaurantByPostalCode.emit(
                        restaurantRepository.getRestaurantByPostalCode(postalCode).fold(
                            onSuccess = {
                                Resource.Success(it)
                            },
                            onFailure = {
                                Resource.Error(it.message.toString())
                            }
                        )
                    )
                }
            }

        }
    }


}