package com.kdbrian.menusmvp.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserAccount(
    @SerializedName("username")
    val email: String,
    val phone: String,
    val avatarUrl: String
)
