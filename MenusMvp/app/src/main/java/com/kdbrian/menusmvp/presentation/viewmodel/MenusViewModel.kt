package com.kdbrian.menusmvp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.menusmvp.domain.menus.MenuRepository
import com.kdbrian.menusmvp.presentation.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import src.main.graphql.GetAllMenusQuery
import src.main.graphql.GetMenuByIdQuery

class MenusViewModel(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _menus: MutableStateFlow<Resource<GetAllMenusQuery.Data>> =
        MutableStateFlow(Resource.Nothing())
    val menus: StateFlow<Resource<GetAllMenusQuery.Data>>
        get() = _menus.asStateFlow()


    private val _menuById: MutableStateFlow<Resource<GetMenuByIdQuery.Data>> =
        MutableStateFlow(Resource.Nothing())
    val menuById: StateFlow<Resource<GetMenuByIdQuery.Data>>
        get() = _menuById.asStateFlow()


    init {
        viewModelScope.launch {
            _menus.emit(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                menuRepository.getAllMenus().fold(
                    onSuccess = {
                        _menus.emit(Resource.Success(it))
                    },
                    onFailure = {
                        _menus.emit(Resource.Error(it.message.toString()))
                    }
                )
            }
        }
    }


    fun getMenuById(id: String) {
        viewModelScope.launch {
            _menuById.emit(Resource.Loading())
            CoroutineScope(Dispatchers.IO).launch {
                _menuById.emit(
                    menuRepository.getMenuById(id).fold(
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