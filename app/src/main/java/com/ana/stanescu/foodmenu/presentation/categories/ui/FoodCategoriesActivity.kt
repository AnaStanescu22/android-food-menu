package com.ana.stanescu.foodmenu.presentation.categories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ana.stanescu.foodmenu.databinding.ActivityFoodMenuBinding

class FoodCategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}