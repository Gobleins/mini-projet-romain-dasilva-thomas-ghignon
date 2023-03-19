package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.TokenEntity

/**
 * Classe modélisant un token utilisateur
 * Classe modélisant les instances de token exposées à l'utilisateur
 */
data class Token(
    val expiresAt: String,
    val requestToken: String
)
internal fun Token.toEntity() = TokenEntity(
    expiresAt = this.expiresAt,
    token = this.requestToken
)