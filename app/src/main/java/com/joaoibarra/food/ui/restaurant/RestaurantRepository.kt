package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.db.restaurant.RestaurantDao
import com.joaoibarra.food.data.remote.IbarraFoodApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RestaurantRepository(
        private val ibarraFoodApi: IbarraFoodApi,
        private val restaurantDao: RestaurantDao
) {
    fun getRestaurants(keyword: String = "") = flow {
        currentCoroutineContext().ensureActive()
        if (restaurantDao.getCount() > 0) {
            restaurantDao.getAll(keyword).collect { emit(it) }
        } else {
            getRestaurantsFromRemote().collect { emit(it) }
        }
    }.flowOn(Dispatchers.IO)

    private fun getRestaurantsFromRemote() = flow<List<Restaurant>> {
        currentCoroutineContext().ensureActive()
        val result = ibarraFoodApi.getRestaurants()
        if (result.isSuccessful) {
            result.body()?.let {
                restaurantDao.insertList(it.toList()).also {
                    restaurantDao.getAll().collect { emit(it) }
                }
            } ?: emit(emptyList())
        } else{
            emit(emptyList())
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