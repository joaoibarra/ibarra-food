package com.joaoibarra.food.di

import com.joaoibarra.food.ui.restaurant.RestaurantListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { RestaurantListViewModel(get()) }
}