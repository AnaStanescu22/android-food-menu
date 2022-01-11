package com.ana.stanescu.foodmenu.model.response

import com.google.gson.annotations.SerializedName

class RandomMeal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strCategory") val category: String
)

data class RandomMealResponse(val meals: List<RandomMeal>)