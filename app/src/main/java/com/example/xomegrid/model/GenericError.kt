package com.example.xomegrid.model

import com.google.gson.annotations.SerializedName

data class GenericError(
    val error: String,
    val statusCode: String
)