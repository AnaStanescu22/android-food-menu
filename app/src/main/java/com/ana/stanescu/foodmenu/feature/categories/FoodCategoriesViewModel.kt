package com.ana.stanescu.foodmenu.feature.categories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ana.stanescu.foodmenu.data.FoodCategoryDao
import com.ana.stanescu.foodmenu.data.FoodCategoryRoomDatabase
import com.ana.stanescu.foodmenu.model.FoodMenuRepository
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodCategoriesViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: FoodMenuRepository by lazy { FoodMenuRepository() }
    private val _state: MutableStateFlow<FoodCategoriesState> =
        MutableStateFlow(FoodCategoriesState(true, emptyList()))

    val state = _state.asStateFlow()

    private lateinit var cachedCategories: List<FoodCategory>

    val loadingState = state.map { value -> value.loading }

    private val dao: FoodCategoryDao by lazy {
        FoodCategoryRoomDatabase.getDatabase(app).itemDao()
    }

    init {
        viewModelScope.launch {
            val categories = getFoodCategories()
            _state.value = FoodCategoriesState(false, categories)
            cachedCategories = categories
        }
    }

    private suspend fun getFoodCategories(): List<FoodCategory> = withContext(Dispatchers.IO){
        return@withContext try {
            val response = repository.service.getMenu()
            dao.insert(response.categories)
            dao.getCategories()
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }

    fun sort(type: SortingType) {
        val currentState = _state.value
        val categories: List<FoodCategory> = currentState.categories
        val sortedList = when (type) {
            SortingType.ALPHABETICALLY_ASCENDING -> categories.sortedBy { it.name }
            SortingType.ALPHABETICALLY_DESCENDING -> categories.sortedByDescending { it.name }
        }
        _state.value = FoodCategoriesState(false, sortedList)
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