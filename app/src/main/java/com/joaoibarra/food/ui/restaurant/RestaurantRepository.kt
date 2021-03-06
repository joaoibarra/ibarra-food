package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.db.restaurant.RestaurantDao
import com.joaoibarra.food.data.remote.IbarraFoodApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RestaurantRepository(
        private val ibarraFoodApi: IbarraFoodApi,
        private val restaurantDao: RestaurantDao
) {
    fun getRestaurants() = flow<List<Restaurant>> {
        val result = ibarraFoodApi.getRestaurants()
        when(result.isSuccessful) {
            true -> {
                result.body()?.let {
                    restaurantDao.insertList(it.toList()).also {
                        emit(restaurantDao.getAll())
                    }
                } ?: emit(emptyList())
            }
            else -> { emit(emptyList())}
        }
    }.flowOn(Dispatchers.IO)
}