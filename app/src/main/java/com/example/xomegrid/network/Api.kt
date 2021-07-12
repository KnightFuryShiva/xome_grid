package com.example.daggerpoc.network

import com.example.xomegrid.model.FlikrModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("/services/rest")
    fun getPictures(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("safe_search") safeSearch: Int,
        @Query("text") text: String
    ): Call<FlikrModel?>?
}