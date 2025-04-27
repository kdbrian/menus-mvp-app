package com.kdbrian.menusmvp.data.remote

import okhttp3.OkHttpClient
import timber.log.Timber

object OkHttpProvider {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            Timber.d("[${it.request().method.uppercase()}] ${it.request().url}")
            it.proceed(it.request())
        }
//        .certificatePinner(CertificatePinner.Builder()
//            .build())
        .build()

}