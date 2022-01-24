package com.ana.stanescu.foodmenu.presentation.categories.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ana.stanescu.foodmenu.presentation.categories.ui.FoodCategoriesViewModel
import com.ana.stanescu.foodmenu.usecase.FoodCategoryUseCase
import com.ana.stanescu.foodmenu.usecase.SearchFoodUseCase
import com.ana.stanescu.foodmenu.usecase.SortUseCase

class FoodCategoryViewModelFactory(
    private val foodCategoryUseCase: FoodCategoryUseCase,
    private val sortUseCase: SortUseCase,
    private val searchFoodUseCase: SearchFoodUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodCategoriesViewModel::class.java)) {
            return FoodCategoriesViewModel(
                foodCategoryUseCase, sortUseCase,
                searchFoodUseCase
            ) as T
        }
        throw IllegalAccessException("invalid viewmodel")
    }
}