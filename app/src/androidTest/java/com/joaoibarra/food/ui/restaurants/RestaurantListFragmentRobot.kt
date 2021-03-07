package com.joaoibarra.food.ui.restaurants

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.joaoibarra.food.R
import com.joaoibarra.food.extensions.loadResponse
import com.joaoibarra.food.ui.restaurant.RestaurantAdapter
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

object RestaurantListFragmentRobot {
    fun arrange(
        func: RestaurantListActivityArrangeRobot.() -> Unit
    ) =
        RestaurantListActivityArrangeRobot().apply { func() }

    fun act(func: RestaurantListActivityActRobot.() -> Unit) =
        RestaurantListActivityActRobot().apply {
            func()
        }

    fun assert(func: RestaurantListActivityAssertRobot.() -> Unit) =
        RestaurantListActivityAssertRobot().apply {
            func()
        }
}

class RestaurantListActivityArrangeRobot() {

    private val sourceResponse by lazy { "mockresponses/restaurants.json".loadResponse() }

    fun mockWebServer(mockWebServer: MockWebServer) {
        val dispatcher = object : Dispatcher() {

            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/v3/1ff35232-3e4a-4feb-8be2-5171256daa86" -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setHeader("Content-Type", "application/json; charset=utf-8")
                            .setBody(sourceResponse)
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
    }
}

class RestaurantListActivityActRobot {
    fun clickItemByPosition(position: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.restaurantList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RestaurantAdapter.RestaurantViewHolder>(
                    position,
                    ViewActions.click()
                )
            )
    }

    fun scrollToText(text: String) {
        Espresso.onView(ViewMatchers.withId(R.id.restaurantList))
            .perform(
                RecyclerViewActions.scrollTo<RestaurantAdapter.RestaurantViewHolder>(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(text)
                    )
                )
            )
    }
}

class RestaurantListActivityAssertRobot {
    fun isRestaurantListVisible(text: String) {
        Espresso.onView(
            ViewMatchers.withText(text)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}