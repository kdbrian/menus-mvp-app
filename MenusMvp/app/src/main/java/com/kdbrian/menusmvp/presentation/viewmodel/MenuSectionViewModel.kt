package com.kdbrian.menusmvp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.menusmvp.domain.menus.MenuSectionRepository
import com.kdbrian.menusmvp.presentation.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import src.main.graphql.GetAllMenuSectionsQuery
import src.main.graphql.GetSectionsByIdQuery
import src.main.graphql.GetSectionsFromMenuQuery
import timber.log.Timber

class MenuSectionViewModel(
    private val menuSectionRepository: MenuSectionRepository,
) : ViewModel() {


    private val _menuSections: MutableStateFlow<Resource<GetAllMenuSectionsQuery.Data>> =
        MutableStateFlow(Resource.Nothing())
    val menuSections: StateFlow<Resource<GetAllMenuSectionsQuery.Data>>
        get() = _menuSections.asStateFlow()

    private val _sectionById: MutableStateFlow<Resource<GetSectionsByIdQuery.Data>> =
        MutableStateFlow(Resource.Nothing())
    val sectionById: StateFlow<Resource<GetSectionsByIdQuery.Data>>
        get() = _sectionById.asStateFlow()

    private val _sectionByMenuId: MutableStateFlow<Resource<GetSectionsFromMenuQuery.Data>> =
        MutableStateFlow(Resource.Nothing())
    val sectionByMenuId: StateFlow<Resource<GetSectionsFromMenuQuery.Data>>
        get() = _sectionByMenuId.asStateFlow()

    private val supervisor = SupervisorJob() //TODO : research wtf is the effect of this

    init {
        viewModelScope.launch {
            _menuSections.emit(Resource.Loading())
            launch(Dispatchers.IO + supervisor) {
                menuSectionRepository
                    .getAllSections()
                    .fold(
                        onSuccess = {
                            _menuSections.emit(Resource.Success(it))
                        },
                        onFailure = {
                            _menuSections.emit(Resource.Error(it.message.toString()))
                        }
                    )
            }
        }
    }


    fun getSectionById(id: String) {
        viewModelScope.launch {

            _sectionById.emit(Resource.Loading())

            menuSectionRepository
                .getSectionsById(id)
                .fold(
                    onSuccess = {
                        Timber.d("got $it")
                        _sectionById.emit(Resource.Success(it))
                    },
                    onFailure = {
                        Timber.d("failed $it")
                        _sectionById.emit(Resource.Error(it.message.toString()))
                    }
                )
        }
    }

    fun getSectionByMenuId(id: String) {
        viewModelScope.launch {
            menuSectionRepository
                .getSectionsByMenuId(id)
                .fold(
                    onSuccess = {
                        _sectionByMenuId.emit(Resource.Success(it))
                    },
                    onFailure = {
                        Timber.d("failed $it")
                        _sectionByMenuId.emit(Resource.Error(it.message.toString()))
                    }
                )
        }
    }


}