package com.joaoibarra.food

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joaoibarra.food.data.db.AppDatabase
import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.db.restaurant.RestaurantDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var restaurantDao: RestaurantDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        restaurantDao = db.getRestaurantDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun shouldFindRestaurantThatExist() {
        val restaurant = mockk<Restaurant> {
            every { id } returns 27
            every { name } returns "Tanoshii Sushi"
            every { status } returns "open"
            every { bestMatch } returns 0.0f
            every { newest } returns 96.0f
            every { ratingAverage } returns 4.5f
            every { distance } returns 1190f
            every { popularity } returns 17.0f
            every { averageProductPrice } returns 1536f
            every { deliveryCosts } returns 200f
            every { minCost } returns 1000f
        }
        restaurantDao.insert(restaurant)
        val restaurantById = restaurantDao.findById(27)
        Assert.assertThat(restaurantById, Matchers.equalTo(restaurant))
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotFindRestaurantThatNotExist() {
        val restaurant = mockk<Restaurant> {
            every { id } returns 27
            every { name } returns "Tanoshii Sushi"
            every { status } returns "open"
            every { bestMatch } returns 0.0f
            every { newest } returns 96.0f
            every { ratingAverage } returns 4.5f
            every { distance } returns 1190f
            every { popularity } returns 17.0f
            every { averageProductPrice } returns 1536f
            every { deliveryCosts } returns 200f
            every { minCost } returns 1000f
        }
        restaurantDao.insert(restaurant)
        val restaurantById = restaurantDao.findById(28)
        Assert.assertNotEquals(restaurantById, Matchers.equalTo(restaurant))
        Assert.assertEquals(restaurantById, null)
    }
}