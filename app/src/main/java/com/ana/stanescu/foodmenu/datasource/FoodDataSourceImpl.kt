package com.ana.stanescu.foodmenu.datasource

import com.ana.stanescu.foodmenu.domain.FoodCategory
import com.ana.stanescu.foodmenu.framework.db.dao.FoodCategoryDao
import com.ana.stanescu.foodmenu.framework.mapper.fromDomainL
import com.ana.stanescu.foodmenu.framework.mapper.toDomainL
import com.ana.stanescu.foodmenu.framework.mapper.toDomainN
import com.ana.stanescu.foodmenu.framework.network.service.FoodApiService

class FoodDataSourceImpl(
    private val foodApiService: FoodApiService,
    private val dao: FoodCategoryDao
) : FoodDataSource {


    override suspend fun getFoodCategoriesFromNetwork(): List<FoodCategory> {
        val response = foodApiService.getMenu().categories
        return response.map {
            it.toDomainN()
        }
    }


    override suspend fun getFoodCategoryFromLocal(): List<FoodCategory> {
        val food = dao.getCategories()
        return food.map{
            it.toDomainL()
        }
    }

    override suspend fun insertAllFood(insert: List<FoodCategory>) {
        val food = insert.map {
            it.fromDomainL()
        }
        dao.insert(food)
    }

    override suspend fun insertFood(foodCategory: FoodCategory) {
        // not needed right now
    }

    override suspend fun deleteFood(foodCategory: FoodCategory) {
        // not needed right now
    }

    override suspend fun deleteAll() {
        // not needed right now
    }
}