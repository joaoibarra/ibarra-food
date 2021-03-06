package com.joaoibarra.food

import android.app.Application
import com.joaoibarra.food.di.NetworkModule
import com.joaoibarra.food.di.RoomInstrumentalTestModule
import com.joaoibarra.food.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApp)
            modules(listOf(NetworkModule, RoomInstrumentalTestModule, ViewModule))
        }
    }
}