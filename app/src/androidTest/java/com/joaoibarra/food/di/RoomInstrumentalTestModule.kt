package com.joaoibarra.food.di

import androidx.room.Room
import com.joaoibarra.food.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RoomInstrumentalTestModule = module {
    single {
        Room.inMemoryDatabaseBuilder(androidContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    single { get<AppDatabase>().getRestaurantDao() }
}