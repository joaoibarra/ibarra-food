package com.joaoibarra.food

import android.app.Application
import com.joaoibarra.food.di.DatabaseModule
import com.joaoibarra.food.di.NetworkModule
import com.joaoibarra.food.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IbarraFoodApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IbarraFoodApp)
            modules(listOf(NetworkModule, DatabaseModule, ViewModule))
        }

    }
}