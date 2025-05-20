package com.kdbrian.menusmvp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdbrian.menusmvp.data.api.SystemCheck
import com.kdbrian.menusmvp.presentation.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SystemHealthCheckViewModel(
    private val systemCheck: SystemCheck
) : ViewModel() {

    private val _systemStatus: MutableStateFlow<Resource<String>> =
        MutableStateFlow(Resource.Nothing())
    val systemStatus: StateFlow<Resource<String>>
        get() = _systemStatus.asStateFlow()


    init {
        refresh()
    }


    fun refresh() {
        viewModelScope.launch {
            _systemStatus.emit(Resource.Loading())
            launch(Dispatchers.IO) {
//                delay(300)
                _systemStatus.emit(
                    systemCheck.checkHealth().fold(
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