package com.ana.stanescu.foodmenu.feature.categories

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ana.stanescu.foodmenu.model.FoodMenuRepository
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class FoodCategoriesViewModel(private val repository: FoodMenuRepository = FoodMenuRepository()) :
    ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _state: MutableLiveData<FoodCategoriesState> =
        MutableLiveData(FoodCategoriesState(true, listOf()))

    val state: LiveData<FoodCategoriesState>
        get() = _state

    init {
        viewModelScope.launch(errorHandler) {
            val categories = repository.getFoodCategories().categories
            _state.value = FoodCategoriesState(false, categories)
        }
    }
}

data class FoodCategoriesState(val loading: Boolean, val categories: List<FoodCategory>)