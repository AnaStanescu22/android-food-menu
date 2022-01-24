package com.ana.stanescu.foodmenu.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodCategory(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
) : Parcelable