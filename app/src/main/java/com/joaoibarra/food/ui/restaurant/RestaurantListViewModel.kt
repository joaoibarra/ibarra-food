package com.joaoibarra.food.ui.restaurant

import androidx.lifecycle.*
import com.joaoibarra.food.data.db.restaurant.Restaurant
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class RestaurantListViewModel(
    private val repository: RestaurantRepository
): ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants


    init {
        fetchRestaurants()
    }

    fun fetchRestaurants(keyword: String = "") {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getRestaurants(keyword).collect {
                _restaurants.postValue(it)
            }
            currentCoroutineContext().cancel()
        }
    }

    fun toogleFavoriteRestaurant(restaurant: Restaurant) {
        viewModelScope.launch (Dispatchers.IO) {
            currentCoroutineContext().ensureActive()
            repository.favoriteRestaurant(restaurant).collect {  }
        }
    }

}