package com.ana.stanescu.foodmenu.framework.db.dao

import androidx.room.*
import com.ana.stanescu.foodmenu.framework.db.model.FoodCategoryEntity

@Dao
interface FoodCategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodCategory: List<FoodCategoryEntity>)

    @Update
    suspend fun update(foodCategory: List<FoodCategoryEntity>)

    @Delete
    suspend fun delete(foodCategory: List<FoodCategoryEntity>)

    @Query("SELECT * from foodCategory")
    suspend fun getCategories(): List<FoodCategoryEntity>
}