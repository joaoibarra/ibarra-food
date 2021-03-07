package com.joaoibarra.food.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaoibarra.food.data.db.restaurant.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val repository: RestaurantRepository
): ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants


    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getRestaurants().collect { _restaurants.postValue(it) }
        }
    }

    fun toogleFavoriteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch (Dispatchers.IO) {
            repository.favoriteRestaurant(restaurant).collect {  }
        }
    }
}