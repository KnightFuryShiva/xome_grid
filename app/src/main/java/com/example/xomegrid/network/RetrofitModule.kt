package com.example.daggerpoc.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitModule {
    val BASE_URL = "https://api.flickr.com"

    fun provideRetrofitService(): Api? {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }
}