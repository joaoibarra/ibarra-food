package com.joaoibarra.food.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaoibarra.food.data.db.restaurant.Restaurant
import com.joaoibarra.food.databinding.ItemRestaurantListBinding


class RestaurantAdapter(
    private val restaurantListViewModel: RestaurantListViewModel,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    private var mDiffer = AsyncListDiffer(this, DiffCallBack)

    fun submitList(list: List<Restaurant>) {
        mDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        ItemRestaurantListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).also {
            it.vm = restaurantListViewModel
            it.lifecycleOwner = lifecycleOwner
            return RestaurantViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        mDiffer.currentList[position].let { holder.bind(it) }
    }

    override fun getItemCount() = mDiffer.currentList.size

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

    object DiffCallBack: DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItemPosition: Restaurant, newItemPosition: Restaurant): Boolean {
            return oldItemPosition.id == newItemPosition.id
        }

        override fun areContentsTheSame(oldItemPosition: Restaurant, newItemPosition: Restaurant): Boolean {
            return oldItemPosition.equals(newItemPosition)
        }
    }

}