package com.ana.stanescu.foodmenu.datasource

import com.ana.stanescu.foodmenu.domain.FoodCategory

interface FoodDataSource {
    // network Response
    suspend fun getFoodCategoriesFromNetwork(): List<FoodCategory>

    // local cache
    suspend fun insertFood(foodCategory: FoodCategory)
    suspend fun getFoodCategoryFromLocal(): List<FoodCategory>
    suspend fun insertAllFood(insert: List<FoodCategory>)
    suspend fun deleteFood(foodCategory: FoodCategory)
    suspend fun deleteAll()
}