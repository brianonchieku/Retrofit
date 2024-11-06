package com.example.cat.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cat.utils.RetrofitInstance
import kotlinx.coroutines.launch

class CatViewModel: ViewModel() {
    val catApi = RetrofitInstance.api
    private val _catResults = MutableLiveData<NetworkResponse<CatFacts>>()
    val catResults: LiveData<NetworkResponse<CatFacts>> = _catResults

    fun getFacts(){

        _catResults.value= NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val results = catApi.getRandomFact()
                if (results.isSuccessful){
                    results.body()?.let {
                        _catResults.value = NetworkResponse.Success(it)
                    }
                }else{
                    _catResults.value = NetworkResponse.Error("Error fetching data")
                }
            }catch (e: Exception){
                _catResults.value = NetworkResponse.Error("Error fetching data")
            }


        }
    }
}