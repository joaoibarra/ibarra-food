package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.db.restaurant.RestaurantDao
import com.joaoibarra.food.data.remote.IbarraFoodApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RestaurantRepository(
        private val ibarraFoodApi: IbarraFoodApi,
        private val restaurantDao: RestaurantDao
) {
    fun getRestaurants() = flow<List<Restaurant>> {
        restaurantDao.getAll().collect {
            if (it.isNotEmpty()) {
                emit(it)
            } else {
                getRestaurantsFromRemote().collect {
                    emit(it)
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun getRestaurantsFromRemote() = flow<List<Restaurant>> {
        val result = ibarraFoodApi.getRestaurants()
        when(result.isSuccessful) {
            true -> {
                result.body()?.let {
                    restaurantDao.insertList(it.toList()).also {
                        restaurantDao.getAll()
                    }
                } ?: emit(emptyList())
            }
            else -> { emit(emptyList())}
        }
    }.flowOn(Dispatchers.IO)

    suspend fun favoriteRestaurant(restaurant: Restaurant) = flow<Restaurant> {
        restaurant.apply {
            this.isFavorite = !this.isFavorite
            restaurantDao.update(this)
            emit(restaurant)
        }
    }.flowOn(Dispatchers.IO)
}