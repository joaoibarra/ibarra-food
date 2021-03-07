package com.joaoibarra.food.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.db.restaurant.RestaurantDao

@Database(entities = [Restaurant::class], version = AppDatabase.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRestaurantDao(): RestaurantDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "ibarra_food.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
                DB_NAME
            ).build()
    }
}