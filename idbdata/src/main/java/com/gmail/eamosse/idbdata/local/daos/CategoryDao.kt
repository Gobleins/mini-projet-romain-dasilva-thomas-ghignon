package com.gmail.eamosse.idbdata.local.daos

import androidx.room.*
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.local.entities.CategoryEntity
import com.gmail.eamosse.idbdata.local.entities.MovieWithActorsAndCategory
import retrofit2.http.GET

@Dao
internal interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM category WHERE category_id = :id")
    fun getCategory(id: Int): CategoryEntity?
}