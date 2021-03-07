package com.joaoibarra.food.data.db.restaurant

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant WHERE name LIKE '%' || :keyword || '%' ORDER BY isFavorite DESC, name ASC, distance ASC")
    fun getAll(keyword: String = "") : Flow<List<Restaurant>>

    @Query("SELECT * FROM restaurant WHERE id LIKE :restaurantId")
    fun findById(restaurantId: Int): Restaurant?

    @Query("SELECT COUNT() FROM restaurant")
    fun getCount() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(restaurant: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(restaurant: List<Restaurant>)

    @Update
    fun update(restaurant: Restaurant)
}