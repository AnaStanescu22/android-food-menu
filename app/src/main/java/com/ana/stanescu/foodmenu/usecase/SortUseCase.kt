package com.ana.stanescu.foodmenu.usecase

import com.ana.stanescu.foodmenu.datasource.FoodDataSource
import com.ana.stanescu.foodmenu.domain.FoodCategory
import com.ana.stanescu.foodmenu.domain.SortingType

class SortUseCase(
    private val foodDataSource: FoodDataSource
) {

    suspend operator fun invoke(type: SortingType): List<FoodCategory> {
        val categories = foodDataSource.getFoodCategoryFromLocal()
        return when (type) {
            SortingType.ALPHABETICALLY_ASCENDING -> categories.sortedBy { it.name }
            SortingType.ALPHABETICALLY_DESCENDING -> categories.sortedByDescending { it.name }
        }
    }
}