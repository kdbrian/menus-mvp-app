package com.kdbrian.menusmvp.data.api

import com.kdbrian.menusmvp.data.remote.util.ApiErrorHandler
import com.kdbrian.menusmvp.domain.api.ApiTest
import kotlinx.serialization.Serializable


@Serializable
data class ActuatorHealthStatus(val status: String)

interface SystemCheck {
    suspend fun checkHealth(): Result<String>
}

class SystemCheckImpl(
    private val apiTest: ApiTest
) : SystemCheck {
    override suspend fun checkHealth(): Result<String> {
        return try {
            val health = apiTest.health()
            if (health.isSuccessful) {
                Result.success(health.body()?.status.toString())
            } else {
                Result.failure(Exception(health.errorBody().toString()))
            }
        } catch (e: Exception) {
            ApiErrorHandler.handleError(e)
        }
    }
}
