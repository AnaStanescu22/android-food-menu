package com.ana.stanescu.foodmenu.model

import com.ana.stanescu.foodmenu.model.response.FoodCategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class FoodMenuRepository {
    private val API_URL = "https://www.themealdb.com/api/json/v1/1/"

     val service: Service

    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
        service = retrofit.create(Service::class.java)
    }

    interface Service {
        @GET("categories.php")
        suspend fun getMenu(): FoodCategoriesResponse
    }
}