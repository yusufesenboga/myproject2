package com.ahmetgur.ahmetgurandroidtask

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: SimpsonApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.duckduckgo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpsonApi::class.java)
    }
}