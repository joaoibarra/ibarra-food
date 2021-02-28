package com.joaoibarra.food.data.remote.domain.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantData (
    @SerializedName("name") val name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("sortingValues") val sortingValues: SortingValueData?
)