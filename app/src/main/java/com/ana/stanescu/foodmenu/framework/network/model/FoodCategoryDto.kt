package com.ana.stanescu.foodmenu.framework.network.model

import com.google.gson.annotations.SerializedName

data class FoodCategoryDto(
    @SerializedName("idCategory")
    val id: String,

    @SerializedName("strCategory")
    val name: String,

    @SerializedName("strCategoryThumb")
    val imageUrl: String,

    @SerializedName("strCategoryDescription")
    val description: String,
)