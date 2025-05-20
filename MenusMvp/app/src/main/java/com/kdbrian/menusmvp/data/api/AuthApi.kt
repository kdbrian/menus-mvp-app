package com.kdbrian.menusmvp.data.api

import com.kdbrian.menusmvp.domain.model.ApiResponse
import com.kdbrian.menusmvp.domain.model.UserAccount
import retrofit2.Response
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun register() : Response<ApiResponse<UserAccount>>

    @PATCH("update")
    suspend fun update() : Response<ApiResponse<UserAccount>>

    @POST("login")
    suspend fun login() : Response<ApiResponse<UserAccount>>
//
//    @POST("login")
//    suspend fun login() : Response<ApiResponse<UserAccount>>
}