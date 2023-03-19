package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class CategoryResponse(
    @SerializedName("category")
    val category: List<Category>
) {
    data class Category(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}

internal fun CategoryResponse.Category.toCategory(): Category {
    return Category(
        id = this.id,
        name = this.name
    )
}

internal fun CategoryResponse.Category.toEntity(): Category {
    return Category(
        id = this.id,
        name = this.name
    )
}


