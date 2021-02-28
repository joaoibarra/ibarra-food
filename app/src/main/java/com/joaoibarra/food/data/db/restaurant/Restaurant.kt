package com.joaoibarra.food.data.db.restaurant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "bestMatch") val bestMatch: Float?,
    @ColumnInfo(name = "newest") val newest: Float?,
    @ColumnInfo(name = "ratingAverage") val ratingAverage: Float?,
    @ColumnInfo(name = "distance") val distance: Float?,
    @ColumnInfo(name = "popularity") val popularity: Float?,
    @ColumnInfo(name = "averageProductPrice") val averageProductPrice: Float?,
    @ColumnInfo(name = "deliveryCosts") val deliveryCosts: Float?,
    @ColumnInfo(name = "minCost") val minCost: Float?
)