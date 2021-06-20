package com.ana.stanescu.foodmenu.model.response

import com.google.gson.annotations.SerializedName

data class FoodCategory(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryThumb") val imageUrl: String,
    @SerializedName("strCategoryDescription") val description: String,
)

data class FoodCategoriesResponse(val categories: List<FoodCategory>)