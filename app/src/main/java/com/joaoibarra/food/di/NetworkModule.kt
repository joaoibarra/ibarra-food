package com.joaoibarra.food.di

import com.joaoibarra.food.data.remote.IbarraFoodOkHttpClient
import com.joaoibarra.food.data.remote.IbarraFoodRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val NetworkModule = module {
    single {
        IbarraFoodRetrofit.newInstance(get())
        IbarraFoodOkHttpClient.newInstance(androidContext())
    }
}