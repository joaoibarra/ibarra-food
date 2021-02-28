package com.joaoibarra.food.data.remote.domain.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantListResponse (
    @SerializedName("restaurants") val restaurants: List<RestaurantData>?
)