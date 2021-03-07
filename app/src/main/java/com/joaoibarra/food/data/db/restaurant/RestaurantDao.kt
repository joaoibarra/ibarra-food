package com.joaoibarra.food.data.db.restaurant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import javax.sql.DataSource

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getAll(): List<Restaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant WHERE id LIKE :restaurantId")
    fun findById(restaurantId: Int): Restaurant?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(restaurant: List<Restaurant>)
}