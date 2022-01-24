package com.ana.stanescu.foodmenu.framework.network.service

import com.ana.stanescu.foodmenu.framework.network.model.FoodCategoryResponseDto
import retrofit2.http.GET

interface FoodApiService {
    @GET("categories.php")
    suspend fun getMenu(): FoodCategoryResponseDto
}