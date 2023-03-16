package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.eamosse.idbdata.data.Category

@Entity(
    tableName = "category"
)
internal data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)

internal fun CategoryEntity.toCategory(): Category {
    return Category(
        id = this.id,
        name = this.name
    )
}