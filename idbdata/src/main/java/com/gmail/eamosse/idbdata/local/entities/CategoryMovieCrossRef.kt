package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "category_movie_cross_ref",
    primaryKeys = ["movie_id", "category_id"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movie_id"],
            childColumns = ["category_id"]
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["category_id"],
            childColumns = ["movie_id"]
        )
    ]
)
data class CategoryMovieCrossRef(
    val movie_id: Int,
    val category_id: Int
)