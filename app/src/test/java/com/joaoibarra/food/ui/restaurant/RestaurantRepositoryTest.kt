package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.remote.IbarraFoodApi
import com.joaoibarra.food.data.remote.Result
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class RestaurantRepositoryTest {
    @MockK
    lateinit var ibarraFoodApi: IbarraFoodApi

    @MockK
    lateinit var restaurantRepository: RestaurantRepository

    @MockK
    lateinit var mockException: HttpException

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    private val restaurantsResponseSuccess = Result.Success(restaurantResponse)

    @Test
    fun `when api returns 401 should return empty list`() =
        runBlocking {
            mockException = mockk {
                every { code() } returns 401
            }
            val restaurantsResponseError = Result.Error(mockException)

            ibarraFoodApi = mockk {
                every { getRestaurants() } returns restaurantsResponseError
            }
            assertNotEquals(restaurantsResponseSuccess, ibarraFoodApi.getRestaurants())
            assertEquals(restaurantsResponseError, ibarraFoodApi.getRestaurants())
        }

    @Test
    fun `when api returns 500 should return empty list`() =
        runBlocking {
            mockException = mockk {
                every { code() } returns 500
            }
            val restaurantsResponseError = Result.Error(mockException)

            ibarraFoodApi = mockk {
                every { getRestaurants() } returns restaurantsResponseError
            }
            assertNotEquals(restaurantsResponseSuccess, ibarraFoodApi.getRestaurants())
            assertEquals(restaurantsResponseError, ibarraFoodApi.getRestaurants())
        }

    @Test
    fun `when api returns 200 should return a list`() =
        runBlocking {
            val restaurantsResponseError = Result.Success(restaurantsResponseSuccess)

            ibarraFoodApi = mockk {
                every { getRestaurants() } returns restaurantsResponseSuccess
            }
            assertEquals(restaurantsResponseSuccess, ibarraFoodApi.getRestaurants())
            assertNotEquals(restaurantsResponseError, ibarraFoodApi.getRestaurants())
        }

    @Test
    fun `when api returns with success repository should return a list`() =
        runBlockingTest {
            restaurantRepository = mockk {
                every { getRestaurants() } returns flow { emit(restaurants) }
            }
            restaurantRepository.getRestaurants().collect {
                assertEquals(restaurants, it)
                assertNotEquals(emptyList<Restaurant>(), it)
            }
        }

    @Test
    fun `when api returns with success repository should return a empty list`() =
        runBlockingTest {
            restaurantRepository = mockk {
                every { getRestaurants() } returns flow { emit(emptyList<Restaurant>()) }
            }
            restaurantRepository.getRestaurants().collect {
                assertNotEquals(restaurants, it)
                assertEquals(emptyList<Restaurant>(), it)
            }
        }
}