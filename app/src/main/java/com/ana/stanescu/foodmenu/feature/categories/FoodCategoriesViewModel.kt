package com.ana.stanescu.foodmenu.feature.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ana.stanescu.foodmenu.model.FoodMenuRepository
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FoodCategoriesViewModel(private val repository: FoodMenuRepository = FoodMenuRepository()) :
    ViewModel() {

    private val _state: MutableStateFlow<FoodCategoriesState> =
        MutableStateFlow(FoodCategoriesState(true, listOf()))

    val state = _state.asStateFlow()

    private lateinit var cachedCategories: List<FoodCategory>

    val loadingState = state.map { value -> value.loading == true }

    init {
        viewModelScope.launch {
            val categories = repository.getFoodCategories()
            _state.value = FoodCategoriesState(false, categories)
            cachedCategories = categories
        }
    }

    fun sort(type: SortingType) {
        val currentState = _state.value
        if (currentState != null) {
            val categories: List<FoodCategory> = currentState.categories
            val sortedList = when (type) {
                SortingType.ALPHABETICALLY_ASCENDING -> categories.sortedBy { it.name }
                SortingType.ALPHABETICALLY_DESCENDING -> categories.sortedByDescending { it.name }
            }
            _state.value = FoodCategoriesState(false, sortedList)
        }
    }

    fun filter(newText: String) {
        val categories: List<FoodCategory> = cachedCategories
            .filter { p: FoodCategory ->
                p.name.lowercase().contains(newText.lowercase())
            }
        _state.value = FoodCategoriesState(false, categories)
    }
}

enum class SortingType {
    ALPHABETICALLY_ASCENDING, ALPHABETICALLY_DESCENDING
}

data class FoodCategoriesState(val loading: Boolean, val categories: List<FoodCategory>)