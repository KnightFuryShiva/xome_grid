package com.example.daggerpoc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpoc.network.NetworkRepo
import com.example.xomegrid.model.FlikrModel
import com.example.xomegrid.model.GenericError
import java.lang.Error

public class MainViewModel : ViewModel() {
    val errorValidation = MutableLiveData<GenericError>()
    val success = MutableLiveData<FlikrModel>()
    private var mNetworkRepo: NetworkRepo = NetworkRepo()
    fun searchForImages(input: String) {
        mNetworkRepo.webcall(input) {
            when (it) {
                is FlikrModel -> {
                    success.value = it
                }
                is GenericError -> {
                    errorValidation.value = it
                }
            }

        }
    }
}