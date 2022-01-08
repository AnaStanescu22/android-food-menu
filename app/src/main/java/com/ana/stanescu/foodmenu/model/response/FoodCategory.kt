package com.ana.stanescu.foodmenu.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foodCategory")
data class FoodCategory(
    @PrimaryKey()
    @SerializedName("idCategory") val id: String,
    @ColumnInfo(name = "name")
    @SerializedName("strCategory") val name: String,
    @ColumnInfo(name = "imageUrl")
    @SerializedName("strCategoryThumb") val imageUrl: String,
    @ColumnInfo(name = "description")
    @SerializedName("strCategoryDescription") val description: String,
)

data class FoodCategoriesResponse(val categories: List<FoodCategory>)