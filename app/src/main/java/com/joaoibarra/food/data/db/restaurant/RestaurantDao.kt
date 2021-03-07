package com.joaoibarra.food.data.db.restaurant

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getAll(): Flow<List<Restaurant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant WHERE id LIKE :restaurantId")
    fun findById(restaurantId: Int): Restaurant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(restaurant: List<Restaurant>)

    @Update
    fun update(restaurant: Restaurant)
}