package com.kdbrian.menusmvp.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.kdbrian.menusmvp.BuildConfig
import com.kdbrian.menusmvp.data.remote.OkHttpProvider.okHttpClient
import okhttp3.OkHttpClient
import timber.log.Timber

object GraphqlServiceProvider {
    val menusService = ApolloClient.Builder()
        .okHttpClient(okHttpClient)
        .serverUrl("${BuildConfig.serverUrl}${BuildConfig.graphPath}")
        .build()
}