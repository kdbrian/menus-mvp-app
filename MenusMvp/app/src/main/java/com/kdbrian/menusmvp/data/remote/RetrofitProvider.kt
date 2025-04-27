package com.kdbrian.menusmvp.data.remote

import com.kdbrian.menusmvp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.serverUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpProvider.okHttpClient)
        .build()

}