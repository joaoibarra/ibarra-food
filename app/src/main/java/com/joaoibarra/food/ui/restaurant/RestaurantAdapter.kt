package com.joaoibarra.food.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.databinding.ItemRestaurantListBinding

class RestaurantAdapter(
    private val restaurants: List<Restaurant>,
    private val restaurantListViewModel: RestaurantListViewModel
): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        ItemRestaurantListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).also {
            it.vm = restaurantListViewModel
            return RestaurantViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        restaurants[position].let { holder.bind(it) }
    }

    override fun getItemCount() = restaurants.size

    class RestaurantViewHolder(
        private val binding: ItemRestaurantListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.apply {
                this.restaurant = restaurant
                executePendingBindings()
            }
        }
    }
}