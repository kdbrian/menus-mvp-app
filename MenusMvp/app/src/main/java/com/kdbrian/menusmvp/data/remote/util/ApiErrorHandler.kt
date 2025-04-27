package com.kdbrian.menusmvp.data.remote.util

import java.net.SocketTimeoutException

object ApiErrorHandler {


    inline fun <reified T> handleError(e: Throwable): Result<T> {
        return when (e) {
            is SocketTimeoutException -> Result.failure(Exception("Failed to contact servers. Try again later."))
            else -> Result.failure(Exception("Its us not You"))
        }
    }
}