package com.ana.stanescu.foodmenu.presentation

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.ana.stanescu.foodmenu.datasource.FoodDataSource
import com.ana.stanescu.foodmenu.datasource.FoodDataSourceImpl
import com.ana.stanescu.foodmenu.framework.db.AppDatabase
import com.ana.stanescu.foodmenu.framework.db.dao.FoodCategoryDao
import com.ana.stanescu.foodmenu.framework.network.instance.FoodMenuInstance
import com.ana.stanescu.foodmenu.framework.network.service.FoodApiService
import com.ana.stanescu.foodmenu.presentation.categories.util.FoodCategoryViewModelFactory
import com.ana.stanescu.foodmenu.usecase.FoodCategoryUseCase
import com.ana.stanescu.foodmenu.usecase.SearchFoodUseCase
import com.ana.stanescu.foodmenu.usecase.SortUseCase


class FoodMenuApplication : Application() {

    private lateinit var dao: FoodCategoryDao
    private lateinit var appDataBase: AppDatabase
    private lateinit var apiService: FoodApiService
    private lateinit var foodDataSource: FoodDataSource
    private lateinit var foodCategoryUseCase: FoodCategoryUseCase
    private lateinit var sortUseCase: SortUseCase
    private lateinit var searchFoodUseCase: SearchFoodUseCase
    lateinit var foodViewModelFactory: ViewModelProvider.Factory

    override fun onCreate() {
        super.onCreate()
        appDataBase = AppDatabase.invoke(this)
        dao = appDataBase.itemDao()
        apiService = FoodMenuInstance.instance
        foodDataSource = FoodDataSourceImpl(apiService, dao)
        foodCategoryUseCase = FoodCategoryUseCase(foodDataSource)
        sortUseCase = SortUseCase(foodDataSource)
        searchFoodUseCase = SearchFoodUseCase(foodDataSource)
        foodViewModelFactory = FoodCategoryViewModelFactory(
            foodCategoryUseCase,
            sortUseCase,
            searchFoodUseCase
        )
    }

}