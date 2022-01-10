package com.ana.stanescu.foodmenu.feature.categories

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ana.stanescu.foodmenu.R
import com.ana.stanescu.foodmenu.databinding.ActivityFoodMenuBinding

class FoodCategoriesActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    private val viewModel by viewModels<FoodCategoriesViewModel>()
    private lateinit var foodCategoriesAdapter: FoodCategoriesRecyclerAdapter

    private lateinit var binding: ActivityFoodMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.foodCategoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        foodCategoriesAdapter = FoodCategoriesRecyclerAdapter()
        binding.foodCategoriesRecyclerView.adapter = foodCategoriesAdapter

        viewModel.state.observe(this) { foodCategoriesState ->
            foodCategoriesAdapter.setFoodCategories(foodCategoriesState.categories)

            if (foodCategoriesState.loading)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sort_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.search).actionView as SearchView)
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(this@FoodCategoriesActivity as SearchView.OnQueryTextListener)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.filter(newText!!)
        return false
    }
}