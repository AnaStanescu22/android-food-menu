package com.ana.stanescu.foodmenu.presentation.categories.state

import android.os.Parcelable
import com.ana.stanescu.foodmenu.domain.FoodCategory
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodCategoryUiState(
    val loading: Boolean = false,
    val categories: List<FoodCategory> = listOf()
) : Parcelable