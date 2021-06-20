package com.ana.stanescu.foodmenu.feature.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ana.stanescu.foodmenu.R
import kotlinx.android.synthetic.main.activity_food_menu.*
import kotlinx.coroutines.launch

class FoodCategoriesActivity : AppCompatActivity() {
    private val viewModel by viewModels<FoodCategoriesViewModel>()
    private lateinit var foodCategoriesAdapter: FoodCategoriesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu)

        foodCategoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        foodCategoriesAdapter = FoodCategoriesRecyclerAdapter()
        foodCategoriesRecyclerView.adapter = foodCategoriesAdapter

        viewModel.state.observe(this) { foodCategoriesState ->
            foodCategoriesAdapter.setFoodCategories(foodCategoriesState.categories)

            if (foodCategoriesState.loading)
                loadingProgressBar.visibility = View.VISIBLE
            else
                loadingProgressBar.visibility = View.GONE
        }
    }
}