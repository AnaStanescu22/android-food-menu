package com.ana.stanescu.foodmenu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ana.stanescu.foodmenu.model.response.FoodCategory

@Database(entities = [FoodCategory::class], version = 1, exportSchema = false)
abstract class FoodCategoryRoomDatabase: RoomDatabase() {
    abstract fun itemDao(): FoodCategoryDao
    companion object {
        @Volatile
        private var INSTANCE: FoodCategoryRoomDatabase? = null
        fun getDatabase(context: Context): FoodCategoryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodCategoryRoomDatabase::class.java,
                    "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}