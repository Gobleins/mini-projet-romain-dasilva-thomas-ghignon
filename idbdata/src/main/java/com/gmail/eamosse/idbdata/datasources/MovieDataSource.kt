package com.gmail.eamosse.idbdata.datasources
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.utils.Result

interface MovieDataSource {
    suspend fun getToken(): Result<Token>
    suspend fun saveToken(token: Token)
    suspend fun getCategories(): Result<List<CategoryResponse.Genre>>
}