package com.joaoibarra.food.ui.restaurant

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joaoibarra.food.data.db.restaurant.Restaurant
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RestaurantListViewModelTest {
    @MockK
    private lateinit var restaurantListViewModel: RestaurantListViewModel

    @MockK
    lateinit var restaurantRepository: RestaurantRepository

    @MockK
    private lateinit var restaurantObserver: Observer<List<Restaurant>>

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getRestaurants is called with valid list, then observer is updated with success`() =
        runBlocking {
            restaurantRepository = mockk {
                every { getRestaurants() } returns flow { emit(restaurants) }
            }
            restaurantListViewModel = RestaurantListViewModel(restaurantRepository)
            restaurantListViewModel.restaurants.observeForever(restaurantObserver)
            verify { restaurantRepository.getRestaurants() }
            verify { restaurantObserver.onChanged(restaurants) }
        }

    @Test
    fun `when getRestaurants is called with valid list, then observer is updated with empty list`() =
        runBlocking {
            restaurantRepository = mockk {
                every { getRestaurants() } returns flow { emit(emptyList<Restaurant>()) }
            }
            restaurantListViewModel = RestaurantListViewModel(restaurantRepository)
            restaurantListViewModel.restaurants.observeForever(restaurantObserver)
            verify { restaurantRepository.getRestaurants() }
            verify { restaurantObserver.onChanged(emptyList()) }
        }
}