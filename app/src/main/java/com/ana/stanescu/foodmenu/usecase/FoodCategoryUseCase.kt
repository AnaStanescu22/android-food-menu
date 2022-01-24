package com.ana.stanescu.foodmenu.usecase

import com.ana.stanescu.foodmenu.datasource.FoodDataSource
import com.ana.stanescu.foodmenu.domain.FoodCategory

class FoodCategoryUseCase(
    private val foodDataSource: FoodDataSource
) {
    suspend operator fun invoke(): List<FoodCategory> {
        return try {
            val response = foodDataSource.getFoodCategoriesFromNetwork()
            //insert to local
            foodDataSource.insertAllFood(response)
            //get from local (single source)
            foodDataSource.getFoodCategoryFromLocal()
        } catch (e: Exception) {
            e.printStackTrace()
            foodDataSource.getFoodCategoryFromLocal()
        }

    }

}