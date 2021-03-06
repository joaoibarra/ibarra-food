package com.joaoibarra.food.data.remote.domain.restaurant

import com.google.gson.annotations.SerializedName
import com.joaoibarra.food.data.db.restaurant.Restaurant

data class RestaurantData (
    @SerializedName("name") val name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("sortingValues") val sortingValues: SortingValueData?
) {
    fun toDb() = Restaurant(
        name = name ?: "",
        status = status,
        bestMatch = sortingValues?.bestMatch,
        newest = sortingValues?.newest,
        ratingAverage = sortingValues?.ratingAverage,
        distance = sortingValues?.distance,
        popularity = sortingValues?.popularity,
        averageProductPrice = sortingValues?.averageProductPrice,
        deliveryCosts = sortingValues?.deliveryCosts,
        minCost = sortingValues?.minCost
    )
}