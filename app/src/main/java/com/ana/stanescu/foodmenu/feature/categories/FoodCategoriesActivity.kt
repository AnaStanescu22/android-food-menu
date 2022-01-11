package com.ana.stanescu.foodmenu.feature.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ana.stanescu.foodmenu.databinding.ActivityFoodMenuBinding

class FoodCategoriesActivity : AppCompatActivity() {
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

        viewModel.stateRandomMeal.observe(this) { randomMeal ->
            if (randomMeal != null) {
                binding.categoryTextView.text = randomMeal.name
                binding.mealTextView.text = randomMeal.category
            }
        }
    }
}