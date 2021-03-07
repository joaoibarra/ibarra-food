package com.joaoibarra.food.data.remote

import android.content.Context
import com.joaoibarra.food.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object IbarraFoodOkHttpClient {
    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L

    fun newInstance(context: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(Cache(context.cacheDir, 10L * 1024 * 1024))
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }
}