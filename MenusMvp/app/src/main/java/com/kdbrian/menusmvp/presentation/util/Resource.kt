package com.kdbrian.menusmvp.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Resource<T>(
    val data: T?,
    val message: String?
) {
    class Success<T>(data: T?) : Resource<T>(data = data, message = null)
    class Error<T>(message: String?) : Resource<T>(data = null, message = message)
    class Nothing<T> : Resource<T>(data = null, message = null)
    class Loading<T>(isLoading: Boolean = true) : Resource<T>(data = null, message = null)

}