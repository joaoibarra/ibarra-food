package com.joaoibarra.food.ui.restaurant

import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.data.remote.domain.restaurant.RestaurantListResponse
import io.mockk.every
import io.mockk.mockk

val restaurant1 = mockk<Restaurant> {
    every { id } returns 27
    every { name } returns "Churrascaria e borracharia O rato que ri"
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

val restaurant2 = mockk<Restaurant> {
    every { id } returns 28
    every { name } returns "Cozinha mineira"
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

val restaurants = listOf<Restaurant>(
    restaurant1,
    restaurant2
)

val restaurantResponse = mockk<RestaurantListResponse>()