package com.joaoibarra.food.data.remote.domain.restaurant

import com.google.gson.annotations.SerializedName
import com.joaoibarra.food.data.db.restaurant.Restaurant

data class RestaurantListResponse (
    @SerializedName("restaurants") val restaurants: List<RestaurantData>?
){
    fun toList(): List<Restaurant> {
        return restaurants?.map { it.toDb() } ?: emptyList()
    }
}