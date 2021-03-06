package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.db.restaurant.RestaurantDao
import com.joaoibarra.food.data.remote.IbarraFoodApi
import com.joaoibarra.food.data.remote.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RestaurantRepository(
        private val ibarraFoodApi: IbarraFoodApi,
        private val restaurantDao: RestaurantDao
) {
    fun getRestaurants() = flow<List<Restaurant>> {
        when(val result = ibarraFoodApi.getRestaurants()) {
            is Result.Success -> {
                restaurantDao.insertList(result.data.toList()).also {
                    emit(restaurantDao.getAll())
                }
            }
            is Result.Error -> { emit(emptyList())}
        }
    }.flowOn(Dispatchers.IO)
}