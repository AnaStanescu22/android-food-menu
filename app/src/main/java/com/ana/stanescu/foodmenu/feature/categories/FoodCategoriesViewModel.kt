package com.ana.stanescu.foodmenu.feature.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ana.stanescu.foodmenu.model.FoodMenuRepository
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import kotlinx.coroutines.launch

class FoodCategoriesViewModel(private val repository: FoodMenuRepository = FoodMenuRepository()) :
    ViewModel() {

    private val _state: MutableLiveData<FoodCategoriesState> =
        MutableLiveData(FoodCategoriesState(true, listOf()))

    val state: LiveData<FoodCategoriesState>
        get() = _state

    private lateinit var cachedCategories: List<FoodCategory>

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