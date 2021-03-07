package com.joaoibarra.food.ui.restaurants

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.filters.LargeTest
import com.joaoibarra.food.ui.MainActivity
import com.joaoibarra.food.ui.restaurants.RestaurantListFragmentRobot.act
import com.joaoibarra.food.ui.restaurants.RestaurantListFragmentRobot.arrange
import com.joaoibarra.food.ui.restaurants.RestaurantListFragmentRobot.assert
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest

@LargeTest
@RunWith(JUnit4::class)
class RestaurantListActivityTest: KoinTest {

    private lateinit var mockWebServer: MockWebServer

    private var scenario : ActivityScenario<MainActivity>? = null

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8000)
        Intents.init()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Intents.release()
    }

    @Test
    fun whenOpenScreenShouldDisplay1RestaurantList() {
        arrange {
            mockWebServer(mockWebServer)
            scenario = ActivityScenario.launch(MainActivity::class.java).also {
                runBlocking {  delay(2000) }
            }
        }

        assert {
            isRestaurantListVisible("Daily Sushi")
        }
    }

    @Test
    fun whenOpenScreenShouldDisplayRestaurantItemList() {
        arrange() {
            mockWebServer(mockWebServer)
            scenario = ActivityScenario.launch(MainActivity::class.java).also {
                runBlocking {  delay(2000) }
            }
        }

        act {
            clickItemByPosition(0)
        }
    }
}