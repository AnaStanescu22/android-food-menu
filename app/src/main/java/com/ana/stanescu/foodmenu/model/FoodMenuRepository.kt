package com.ana.stanescu.foodmenu.model

import com.ana.stanescu.foodmenu.BuildConfig
import com.ana.stanescu.foodmenu.FoodMenuApplication
import com.ana.stanescu.foodmenu.data.FoodCategoryDao
import com.ana.stanescu.foodmenu.data.FoodCategoryRoomDatabase
import com.ana.stanescu.foodmenu.model.response.FoodCategoriesResponse
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class FoodMenuRepository {

    private var service: Service
    private var dao: FoodCategoryDao =
        FoodCategoryRoomDatabase.getDatabase(FoodMenuApplication.applicationContext()).itemDao()

    init {
        val retrofit: Retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
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