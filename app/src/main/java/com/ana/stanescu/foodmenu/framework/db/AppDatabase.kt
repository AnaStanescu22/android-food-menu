package com.ana.stanescu.foodmenu.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ana.stanescu.foodmenu.framework.db.dao.FoodCategoryDao
import com.ana.stanescu.foodmenu.framework.db.model.FoodCategoryEntity

@Database(entities = [FoodCategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itemDao(): FoodCategoryDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        operator fun invoke(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}