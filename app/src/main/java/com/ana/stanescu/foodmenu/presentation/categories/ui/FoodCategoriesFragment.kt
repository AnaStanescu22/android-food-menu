package com.ana.stanescu.foodmenu.presentation.categories.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ana.stanescu.foodmenu.presentation.FoodMenuApplication
import com.ana.stanescu.foodmenu.R
import com.ana.stanescu.foodmenu.databinding.FragmentFoodCategoriesBinding
import com.ana.stanescu.foodmenu.domain.SortingType
import com.ana.stanescu.foodmenu.presentation.categories.adapter.FoodCategoriesRecyclerAdapter
import com.ana.stanescu.foodmenu.presentation.categories.state.FoodCategoryUiState

class FoodCategoriesFragment : Fragment() {

    private val KEY = "State_KEY"

    private var _binding: FragmentFoodCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var foodCategoriesAdapter: FoodCategoriesRecyclerAdapter


    private val viewModel: FoodCategoriesViewModel by activityViewModels {
        (activity?.application as FoodMenuApplication).foodViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.getParcelable<FoodCategoryUiState>(KEY)?.let{
            viewModel.handleState(it)
        }
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
        super.onViewCreated(view, savedInstanceState)
        initResView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, { it ->
            it?.let {
                indicator(it.loading)
                foodCategoriesAdapter.setFoodCategories(it.categories)
            }
        })
    }

    private fun initResView() {
        foodCategoriesAdapter = FoodCategoriesRecyclerAdapter()
        binding.foodCategoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = foodCategoriesAdapter
        }
    }

    private fun indicator(isVisible: Boolean) {
        binding.loadingProgressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
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

    override fun onSaveInstanceState(outState: Bundle) {
        val vs = viewModel.state.value
        outState.putParcelable(KEY, vs)
        super.onSaveInstanceState(outState)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}