package com.joaoibarra.food.data.remote.domain.restaurant

import com.google.gson.annotations.SerializedName

class SortingValueData (
    @SerializedName("bestMatch") val bestMatch: Float?,
    @SerializedName("newest") val newest: Float?,
    @SerializedName("ratingAverage") val ratingAverage: Float?,
    @SerializedName("distance") val distance: Float?,
    @SerializedName("popularity") val popularity: Float?,
    @SerializedName("averageProductPrice") val averageProductPrice: Float?,
    @SerializedName("deliveryCosts") val deliveryCosts: Float?,
    @SerializedName("minCost") val minCost: Float?
)