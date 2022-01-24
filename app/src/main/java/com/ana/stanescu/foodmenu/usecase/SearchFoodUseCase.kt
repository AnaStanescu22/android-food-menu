package com.ana.stanescu.foodmenu.usecase

import com.ana.stanescu.foodmenu.datasource.FoodDataSource
import com.ana.stanescu.foodmenu.domain.FoodCategory

class SearchFoodUseCase(
    private val foodDataSource: FoodDataSource
) {
    suspend operator fun invoke(filter: String): List<FoodCategory> {
        val foods = foodDataSource.getFoodCategoryFromLocal()
        return foods.filter {
            it.name.lowercase().contains(filter.lowercase())
        }
    }
}