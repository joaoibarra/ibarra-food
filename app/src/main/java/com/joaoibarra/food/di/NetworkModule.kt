package com.joaoibarra.food.di

import com.joaoibarra.food.data.remote.IbarraFoodOkHttpClient
import com.joaoibarra.food.data.remote.IbarraFoodRetrofit
import com.joaoibarra.food.ui.restaurant.RestaurantRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val NetworkModule = module {
    single { IbarraFoodRetrofit.newInstance(get()) }
    single { IbarraFoodOkHttpClient.newInstance(androidContext())}

    single { RestaurantRepository(get(), get()) }
}