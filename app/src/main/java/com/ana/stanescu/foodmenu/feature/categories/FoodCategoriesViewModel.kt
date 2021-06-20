package com.ana.stanescu.foodmenu.feature.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ana.stanescu.foodmenu.model.FoodMenuRepository
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import kotlinx.coroutines.launch

class FoodCategoriesViewModel(private val repository: FoodMenuRepository = FoodMenuRepository()) :
    ViewModel() {

    val state: MutableLiveData<FoodCategoriesState> =
        MutableLiveData(FoodCategoriesState(true, listOf()))

    init {
        viewModelScope.launch {
            val categories = repository.getFoodCategories().categories
            state.value = FoodCategoriesState(false, categories)
        }
    }
}

data class FoodCategoriesState(val loading: Boolean, val categories: List<FoodCategory>)