package com.joaoibarra.food.ui.restaurant

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.joaoibarra.food.R
import com.joaoibarra.food.databinding.FragmentRestaurantListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListFragment: Fragment() {
    private val restaurantListViewModel: RestaurantListViewModel by viewModel()

    private var binding: FragmentRestaurantListBinding? = null
    private var restaurantAdapter: RestaurantAdapter? = null
    private var searchView: SearchView? = null

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
        setHasOptionsMenu(true)
        setAdapter()
        setObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        restaurantListViewModel.restaurants.removeObservers(requireActivity())
    }

    private fun setAdapter() {
        restaurantAdapter = RestaurantAdapter(
            restaurantListViewModel,
            viewLifecycleOwner
        )
        binding?.adapter = restaurantAdapter
    }

    private fun setObserver() {
        restaurantListViewModel.restaurants.observe(viewLifecycleOwner) { list ->
            restaurantAdapter?.submitList(list)
            restaurantAdapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.restaurant_menu, menu)
        menu.findItem(R.id.action_search)?.let { searchItem ->
            searchView = searchItem.actionView as SearchView
            searchView?.apply {
                setOnCloseListener { true }
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            restaurantListViewModel.fetchRestaurants(it)
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}