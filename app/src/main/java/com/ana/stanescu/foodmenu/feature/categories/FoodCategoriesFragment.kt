package com.ana.stanescu.foodmenu.feature.categories

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ana.stanescu.foodmenu.R
import com.ana.stanescu.foodmenu.databinding.FragmentFoodCategoriesBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FoodCategoriesFragment : Fragment() {
    private var _binding: FragmentFoodCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var foodCategoriesAdapter: FoodCategoriesRecyclerAdapter
    private val viewModel: FoodCategoriesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        foodCategoriesAdapter = FoodCategoriesRecyclerAdapter()
        binding.foodCategoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.foodCategoriesRecyclerView.adapter = foodCategoriesAdapter

        viewModel.state.onEach { foodCategoriesState ->
            foodCategoriesAdapter.setFoodCategories(foodCategoriesState.categories)
        }.launchIn(lifecycleScope)

        viewModel.loadingState.onEach { isLoading ->
            if (isLoading)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.search).actionView as SearchView)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_aToZ -> {
                viewModel.sort(SortingType.ALPHABETICALLY_ASCENDING)
                return true
            }
            R.id.menu_zToa -> {
                viewModel.sort(SortingType.ALPHABETICALLY_DESCENDING)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}