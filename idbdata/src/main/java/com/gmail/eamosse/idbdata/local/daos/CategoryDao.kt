package com.gmail.eamosse.idbdata.local.daos

import androidx.room.*
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.local.entities.CategoryEntity

@Dao
internal interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg category: CategoryEntity)

}