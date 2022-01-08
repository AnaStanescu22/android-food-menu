package com.ana.stanescu.foodmenu.model

import com.ana.stanescu.foodmenu.FoodMenuApplication
import com.ana.stanescu.foodmenu.model.response.FoodCategoriesResponse
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import com.ana.stanescu.foodmenu.data.FoodCategoryDao
import com.ana.stanescu.foodmenu.data.FoodCategoryRoomDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class FoodMenuRepository {
    private val API_URL = "https://www.themealdb.com/api/json/v1/1/"

    private var service: Service
    private var dao: FoodCategoryDao =
        FoodCategoryRoomDatabase.getDatabase(FoodMenuApplication.applicationContext()).itemDao()

    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()
        service = retrofit.create(Service::class.java)
    }


    suspend fun getFoodCategories(): List<FoodCategory> {
        return try {
            val response = service.getMenu()
            dao.insert(response.categories)
            dao.getCategories()
        } catch (e: Exception) {
            dao.getCategories()
        }
    }

    interface Service {
        @GET("categories.php")
        suspend fun getMenu(): FoodCategoriesResponse
    }
}