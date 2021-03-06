package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.remote.IbarraFoodApi
import com.joaoibarra.food.data.remote.domain.restaurant.RestaurantListResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

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

    private val restaurantsResponseSuccess = Response.success(restaurantResponse)

    @Test
    fun `when api returns 401 should return empty list`() =
        runBlocking {
            val restaurantsResponseError = Response.error<RestaurantListResponse>(
                401,
                mockk<ResponseBody>() {
                    every { contentType() } returns "application/json; charset=UTF-8".toMediaType()
                    every { contentLength() } returns 1000L
                }
            )

            ibarraFoodApi = mockk {
                coEvery { getRestaurants() } returns restaurantsResponseError
            }
            assertNotEquals(restaurantsResponseSuccess, ibarraFoodApi.getRestaurants())
            assertEquals(restaurantsResponseError, ibarraFoodApi.getRestaurants())
        }

    @Test
    fun `when api returns 500 should return empty list`() =
        runBlocking {
            val restaurantsResponseError = Response.error<RestaurantListResponse>(
                500,
                mockk<ResponseBody>() {
                    every { contentType() } returns "application/json; charset=UTF-8".toMediaType()
                    every { contentLength() } returns 1000L
                }
            )
            ibarraFoodApi = mockk {
                coEvery { getRestaurants() } returns restaurantsResponseError
            }

            assertNotEquals(restaurantsResponseSuccess, ibarraFoodApi.getRestaurants())
            assertEquals(restaurantsResponseError, ibarraFoodApi.getRestaurants())
        }

    @Test
    fun `when api returns 200 should return a list`() =
        runBlocking {
            val restaurantsResponseError = Response.success(restaurantsResponseSuccess)

            ibarraFoodApi = mockk {
                coEvery { getRestaurants() } returns restaurantsResponseSuccess
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