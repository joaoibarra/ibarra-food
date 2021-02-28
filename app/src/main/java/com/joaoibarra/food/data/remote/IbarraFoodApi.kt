package com.joaoibarra.food.data.remote

import com.joaoibarra.food.data.remote.domain.restaurant.RestaurantListResponse
import retrofit2.Response
import retrofit2.http.GET

interface IbarraFoodApi {
    @GET("v3/1ff35232-3e4a-4feb-8be2-5171256daa86")
    fun getRestaurants(): Response<RestaurantListResponse>
}