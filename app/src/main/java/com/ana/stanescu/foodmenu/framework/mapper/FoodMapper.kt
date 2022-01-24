package com.ana.stanescu.foodmenu.framework.mapper

import com.ana.stanescu.foodmenu.domain.FoodCategory
import com.ana.stanescu.foodmenu.framework.db.model.FoodCategoryEntity
import com.ana.stanescu.foodmenu.framework.network.model.FoodCategoryDto


// for network
fun FoodCategoryDto.toDomainN(): FoodCategory {
    return FoodCategory(
        id, name, imageUrl, description
    )
}

fun FoodCategory.fromDomainN(): FoodCategoryDto {
    return FoodCategoryDto(
        id, name, imageUrl, description
    )
}


// for local
fun FoodCategoryEntity.toDomainL(): FoodCategory {
    return FoodCategory(
        id, name, imageUrl, description
    )
}

fun FoodCategory.fromDomainL(): FoodCategoryEntity {
    return FoodCategoryEntity(id, name, imageUrl, description)
}