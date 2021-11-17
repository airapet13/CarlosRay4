package com.example.carlosray4

import com.example.carlosray4.api.JokeJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET(".")
    fun getJoke(): Call<JokeJson>

}