package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity

@Entity(
    tableName = "serie_category_cross_ref",
    primaryKeys = ["serie_id", "category_id"],
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = SerieEntity::class,
            parentColumns = ["serie_id"],
            childColumns = ["serie_id"]
        ),
        androidx.room.ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"]
        )
    ]
)

internal data class SerieCategoryCrossRef(
    val serie_id: Int,
    val category_id: Int
)

