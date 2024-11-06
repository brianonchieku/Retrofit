package com.example.cat.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cat.utils.RetrofitInstance

class CatViewModel: ViewModel() {
    val catApi = RetrofitInstance.api
    private val _catResults = MutableLiveData<NetworkResponse<CatFacts>>()
    val catResults: LiveData<NetworkResponse<CatFacts>> = _catResults
}