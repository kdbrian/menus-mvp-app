package com.kdbrian.menusmvp.data.remote.util

import com.kdbrian.menusmvp.BuildConfig
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException


object ApiErrorHandler {
    inline fun <reified T> handleError(e: Exception): Result<T> {
        return when (e) {
            is ConnectException -> {
                if (BuildConfig.DEBUG)
                    Result.failure(e)
                else
                    Result.failure(Exception("Failed to contact server. Try again later"))
            }
            is SocketTimeoutException -> {
                if (BuildConfig.DEBUG)
                    Result.failure(e)
                else
                    Result.failure(Exception("Its us not you. Issue forwarded to admin 🙂"))
            }

            else -> {
                Timber.d("Err[${e.javaClass.name}] : ${e.message} ${e.stackTrace[0]}")
                Result.failure<T>(Exception("Ooops something happened"))
            }
        }
    }
}