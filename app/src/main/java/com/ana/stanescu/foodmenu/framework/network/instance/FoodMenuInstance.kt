package com.ana.stanescu.foodmenu.framework.network.instance

import com.ana.stanescu.foodmenu.framework.network.service.FoodApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodMenuInstance {

    private val API_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val instance by lazy {
        retrofitBuilder.create(FoodApiService::class.java)
    }

}