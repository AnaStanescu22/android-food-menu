package com.ana.stanescu.foodmenu.presentation.categories.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ana.stanescu.foodmenu.domain.SortingType
import com.ana.stanescu.foodmenu.presentation.categories.state.FoodCategoryUiState
import com.ana.stanescu.foodmenu.usecase.FoodCategoryUseCase
import com.ana.stanescu.foodmenu.usecase.SearchFoodUseCase
import com.ana.stanescu.foodmenu.usecase.SortUseCase
import kotlinx.coroutines.launch

class FoodCategoriesViewModel(
    private val foodCategoryUseCase: FoodCategoryUseCase,
    private val sortUseCase: SortUseCase,
    private val searchFoodUseCase: SearchFoodUseCase
) : ViewModel() {

    private val _state: MutableLiveData<FoodCategoryUiState> = MutableLiveData()
    val state: LiveData<FoodCategoryUiState> get() = _state

    init {
        getFoodCategories()
    }

    private fun getFoodCategories() {
        viewModelScope.launch {
            val categories = foodCategoryUseCase()
            _state.value = uiState().copy(categories = categories)
        }
    }

    internal fun sort(type: SortingType) {
        viewModelScope.launch {
            val foodCategories = sortUseCase(type)
            _state.value = uiState().copy(categories = foodCategories)
        }

    }

    internal fun filter(newText: String) {
        viewModelScope.launch {
            val categories = searchFoodUseCase(newText)
            _state.value = uiState().copy(categories = categories)
        }
    }

    // to handle state across process death
    internal fun handleState(foodCategoryUiState: FoodCategoryUiState) {
        _state.value = foodCategoryUiState
    }

    private fun uiState(): FoodCategoryUiState {
        return state.value ?: FoodCategoryUiState()
    }

}
