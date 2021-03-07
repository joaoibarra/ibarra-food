package com.joaoibarra.food.di

import com.joaoibarra.food.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule = module {
    single { AppDatabase.getInstance(androidApplication().applicationContext) }
    single { get<AppDatabase>().getRestaurantDao() }
}