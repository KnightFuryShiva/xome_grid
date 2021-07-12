package com.example.xomegrid.model
import com.google.gson.annotations.SerializedName

data class FlikrModel (
	@SerializedName("photos") val photos : Photos,
	@SerializedName("stat") val stat : String
)