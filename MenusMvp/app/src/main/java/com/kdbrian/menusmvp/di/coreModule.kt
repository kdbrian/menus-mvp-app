package com.kdbrian.menusmvp.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.kdbrian.menusmvp.BuildConfig
import com.kdbrian.menusmvp.data.api.SystemCheck
import com.kdbrian.menusmvp.data.api.SystemCheckImpl
import com.kdbrian.menusmvp.domain.api.ApiTest
import com.kdbrian.menusmvp.presentation.viewmodel.SystemHealthCheckViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val coreModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor {
                Timber.d("[${it.request().method.uppercase()}] ${it.request().url}")
                it.proceed(it.request())
            }
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<ApolloClient> {
        ApolloClient.Builder()
            .okHttpClient(get())
            .serverUrl(BuildConfig.serverUrl)
            .build()
    }

    single<SystemCheck> {
        SystemCheckImpl(get<Retrofit>().create(ApiTest::class.java))
    }


    viewModel {
        SystemHealthCheckViewModel(get())
    }
}