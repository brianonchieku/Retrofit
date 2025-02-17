package com.example.cat.data

import com.example.cat.models.CatFacts
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/fact")
    suspend fun getRandomFact(): Response<CatFacts>
}