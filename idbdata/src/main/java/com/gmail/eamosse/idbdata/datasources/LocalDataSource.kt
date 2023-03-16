package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.local.daos.CategoryDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.CategoryEntity
import com.gmail.eamosse.idbdata.local.entities.TokenEntity
import com.gmail.eamosse.idbdata.local.entities.toCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.gmail.eamosse.idbdata.utils.Result

@Singleton
internal class LocalDataSource @Inject constructor(private val tokenDao: TokenDao, private val categoryDao: CategoryDao) :
    MovieDataSource {

    override suspend fun getToken(): Result<Token> = withContext(Dispatchers.IO) {
        tokenDao.retrieve()?.let {
            Result.Succes(it.toToken())
        } ?: Result.Error(
            exception = Exception(),
            message = "You should get a token from the API first",
            code = -1
        )
    }

    override suspend fun saveToken(token: Token) {
        withContext(Dispatchers.IO) {
            tokenDao.insert(token.toEntity())
        }
    }

    override suspend fun getCategories(): Result<List<Category>> = withContext(Dispatchers.IO) {
        val categories = categoryDao.getAll().map {
            it.toCategory()
        }
        Result.Succes(categories)
    }
}

internal fun Token.toEntity() = TokenEntity(
    expiresAt = this.expiresAt,
    token = this.requestToken
)

internal fun TokenEntity.toToken() = Token(
    expiresAt = this.expiresAt,
    requestToken = this.token
)

internal fun Category.toEntity() = CategoryEntity(
    id = this.id,
    name = this.name
)

internal fun CategoryEntity.toCategory() = Category(
    id = this.id,
    name = this.name
)