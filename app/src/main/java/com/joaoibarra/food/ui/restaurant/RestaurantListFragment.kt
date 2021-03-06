package com.joaoibarra.food.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joaoibarra.food.databinding.FragmentRestaurantListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListFragment: Fragment() {
    private val restaurantListViewModel: RestaurantListViewModel by viewModel()

    private var binding: FragmentRestaurantListBinding? = null
    private var restaurantAdapter: RestaurantAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRestaurantListBinding.inflate(inflater, container, false)
        .apply {
            lifecycleOwner = this@RestaurantListFragment
            vm = restaurantListViewModel
            binding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        restaurantListViewModel.restaurants.observe(viewLifecycleOwner) { list ->
            restaurantAdapter = RestaurantAdapter(
                list,
                restaurantListViewModel
            )
            binding?.adapter = restaurantAdapter
        }
    }
}