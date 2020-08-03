package com.ka.testeeasywork.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
)