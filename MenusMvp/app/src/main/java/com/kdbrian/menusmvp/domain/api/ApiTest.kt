package com.kdbrian.menusmvp.domain.api

import com.kdbrian.menusmvp.BuildConfig
import com.kdbrian.menusmvp.data.api.ActuatorHealthStatus
import retrofit2.Response
import retrofit2.http.GET

interface ApiTest {
    @GET(BuildConfig.serverHealthUrl)
    suspend fun health(): Response<ActuatorHealthStatus>
}