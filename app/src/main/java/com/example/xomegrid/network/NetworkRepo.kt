package com.example.daggerpoc.network

import android.util.Log
import com.example.xomegrid.model.FlikrModel
import com.example.xomegrid.model.GenericError
import retrofit2.Call
import retrofit2.Response


class NetworkRepo {
    lateinit var apiInterface: Api

    //method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&safe_search=1&text=kittens
    fun webcall(input: String, onResult: (Any?) -> Unit) {
        apiInterface = RetrofitModule().provideRetrofitService()!!
        if (this::apiInterface.isInitialized) {
            val call: Call<FlikrModel?>? = apiInterface.getPictures(
                "flickr.photos.search",
                "3e7cc266ae2b0e0d78e279ce8e361736",
                "json",
                1,
                1,
                input
            )

            call!!.enqueue(object : retrofit2.Callback<FlikrModel?> {

                override fun onResponse(call: Call<FlikrModel?>, response: Response<FlikrModel?>) {
                    val pictures: FlikrModel? = response.body()
                    onResult(pictures)
                }

                override fun onFailure(call: Call<FlikrModel?>, t: Throwable) {
                    Log.e("", "")
                    val genericError = GenericError("Please try with different senario", "")
                    onResult(genericError)
                }
            })
        }
    }
}