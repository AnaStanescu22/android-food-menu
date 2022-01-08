package com.ana.stanescu.foodmenu

import android.app.Application
import android.content.Context


class FoodMenuApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: FoodMenuApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}