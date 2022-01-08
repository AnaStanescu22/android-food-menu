package com.ana.stanescu.foodmenu.data

import androidx.room.*
import com.ana.stanescu.foodmenu.model.response.FoodCategory

@Dao
interface FoodCategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodCategory: List<FoodCategory>)

    @Update
    suspend fun update(foodCategory: List<FoodCategory>)

    @Delete
    suspend fun delete(foodCategory: List<FoodCategory>)

    @Query("SELECT * from foodCategory")
    suspend fun getCategories(): List<FoodCategory>
}