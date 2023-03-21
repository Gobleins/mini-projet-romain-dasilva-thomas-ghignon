package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.local.entities.CategoryEntity
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class CategoriesResponse(
    @SerializedName("genres")
    val category: List<CategoryResponse>
)
data class CategoryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
){
    internal fun toCategory(): Category {
        return Category(
            id = this.id,
            name = this.name
        )
    }

    internal fun toEntity(): CategoryEntity {
        return CategoryEntity(
            id = this.id,
            name = this.name
        )
    }
}





