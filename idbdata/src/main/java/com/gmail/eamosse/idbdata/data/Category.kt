package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.CategoryEntity

data class Category(
    val id: Int,
    val name: String
)

internal fun Category.toEntity() = CategoryEntity(
    id = this.id,
    name = this.name
)