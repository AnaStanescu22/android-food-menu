package com.ana.stanescu.foodmenu.feature.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ana.stanescu.foodmenu.databinding.ActivityFoodMenuBinding

class FoodCategoriesActivity : AppCompatActivity() {
    private val viewModel by viewModels<FoodCategoriesViewModel>()
    private lateinit var binding: ActivityFoodMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodCategoriesAdapter = FoodCategoriesRecyclerAdapter()
        binding.foodCategoriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FoodCategoriesActivity)
            adapter = foodCategoriesAdapter
        }

        viewModel.state.observe(this) { foodCategoriesState ->
            foodCategoriesAdapter.setFoodCategories(foodCategoriesState.categories).also {
                // This click listener is to help detect when a click is done on a FoodCategory object
                foodCategoriesAdapter.setItemClickListener {
                    Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
                }
            }
            binding.loadingProgressBar.isVisible = foodCategoriesState.loading
        }
    }
}