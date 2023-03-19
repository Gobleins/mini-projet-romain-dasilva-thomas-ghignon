package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.eamosse.idbdata.data.Token

/**
 * Modélise les tokens dans la base de données
 */
@Entity(
    tableName = "idb_token"
)
internal data class TokenEntity(
    @PrimaryKey
    val token: String,
    val expiresAt: String
)

internal fun TokenEntity.toToken() = Token(
    expiresAt = this.expiresAt,
    requestToken = this.token
)